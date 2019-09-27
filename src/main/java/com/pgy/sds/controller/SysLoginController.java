package com.pgy.sds.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pgy.sds.common.AsyncFactory;
import com.pgy.sds.common.AsyncManager;
import com.pgy.sds.common.constant.SysConstants;
import com.pgy.sds.common.utils.MessageUtils;
import com.pgy.sds.dao.SysUserMapper;
import com.pgy.sds.model.*;
import com.pgy.sds.service.SysUserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:24
 */
@Slf4j
@RestController
public class SysLoginController extends AbstractController {

	@Autowired SysUserMapper sysUserMapper;

	@Autowired SysUserTokenService sysUserTokenService;

	@Value(value = "${user.password.maxRetryCount}")
	private String maxRetryCount;

	@PostMapping("/admin/sys/login")
	public Result login(@RequestBody @Valid SysLoginForm form, BindingResult errorResult) {
		if (errorResult.hasErrors()) {
			List<ObjectError> errors = errorResult.getAllErrors();
			List<String> messAges = errors.stream().map(info -> info.getDefaultMessage()).collect(Collectors.toList());
			String msg = StringUtils.collectionToDelimitedString(messAges, ",");
			return Result.error(msg);
		}
		//用户信息
		SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>()
						.lambda()
						.eq(SysUser::getUsername, form.getUsername()));
		if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			//用户名或密码错误
			AsyncManager.me().execute(AsyncFactory.recordLogininfo(user.getUsername(), "fail", MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
			return Result.error(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
		}
		if (user.getStatus().equals(SysConstants.ACCOUNT_LOCKING)) {
			return Result.error(MessageUtils.message("user.blocked"));
		}

		//生成token，并保存到redis
		return sysUserTokenService.createToken(user.getUserId());
	}

	/*上传图片测试*/
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
		String filePath = "/Volumes/pgy/";
		try {
			String fileName = System.currentTimeMillis() + file.getOriginalFilename();
			String pgy_url = req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
			String destFileName = filePath + fileName;
			File destFile = new File(destFileName);
			destFile.getParentFile().mkdirs();
			file.transferTo(destFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*excel测试*/
	@PutMapping("/expor")
	public void exporExcel(HttpServletResponse response) throws IOException {
		ExcelWriter writer = null;
		OutputStream outputStream = response.getOutputStream();
		try {
			//添加响应头信息
			response.setHeader("Content-disposition", "attachment; filename=" + "catagory.xls");
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型
			response.setHeader("Pragma", "No-cache");//设置头
			response.setHeader("Cache-Control", "no-cache");//设置头
			response.setDateHeader("Expires", 0);//设置日期头

			//实例化 ExcelWriter
			writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLS, true);
			//实例化表单
			Sheet sheet = new Sheet(1, 0, SysUser.class);
			sheet.setSheetName("用户信息");
			//获取数据
			List<SysUser> userList = sysUserMapper.queryAllUser();
			//输出
			writer.write(userList, sheet);
			writer.finish();
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			response.getOutputStream().close();
		}
	}
}
