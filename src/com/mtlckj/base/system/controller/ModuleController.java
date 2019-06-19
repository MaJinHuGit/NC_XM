package com.mtlckj.base.system.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mtlckj.base.system.domain.ModuleDO;
import com.mtlckj.base.system.service.ModuleService;
import com.mtlckj.base.system.utils.Doc2html;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.Query;
import com.mtlckj.base.system.utils.R;

@Controller
@RequestMapping(value="/module")
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping(value="/upload")
	@ResponseBody
	public String uploadFile(@RequestParam(name="file",required=false)CommonsMultipartFile file) {
		String filename=file.getOriginalFilename();
		Doc2html doc2html=new Doc2html();
		if(filename.contains("docx")) {
			try {
				doc2html.docxToHtml(file.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(filename.contains("doc")) {
			
		}
		return null;
	}
	
	@PostMapping(value="/save")
	@ResponseBody
	public R saveModule(ModuleDO moduleDO) {
		Long id=0L;
		if((id=moduleService.save(moduleDO))>0) {
			return R.ok(String.valueOf(id));
		}
		
//		if(1>0) {
//			return R.ok(String.valueOf(8L));
//		}
		return R.error("保存模块失败");
	}
	
	@RequestMapping(value="/all")
	@ResponseBody
	public PageUtils getAll(@RequestParam Map<String,Object> params){
		Query query=new Query(params);
		List<ModuleDO> modules=moduleService.list(query);
		int total=moduleService.count(query);
		PageUtils pageUtil=new PageUtils(modules, total);
		return pageUtil;
	}
	
	@PostMapping(value = "/remove")
	@ResponseBody
	public R remove(@RequestParam("id") Long id){
		if(moduleService.remove(id)>0){
			return R.ok("删除成功");
		}
		return R.error("删除失败");
	}
	
	@GetMapping(value="/list")
	@ResponseBody
	public List<ModuleDO> getList(@RequestParam Map<String,Object> params){
		List<ModuleDO> modules=moduleService.list(params);
		return modules;
	}
	
	
	
}
