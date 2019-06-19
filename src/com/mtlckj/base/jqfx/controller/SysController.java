package com.mtlckj.base.jqfx.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.mtlckj.base.jqfx.domain.Fields;
import com.mtlckj.base.jqfx.domain.Sys;
import com.mtlckj.base.jqfx.domain.TableResource;
import com.mtlckj.base.jqfx.service.SysService;
import com.mtlckj.base.system.domain.UserDO;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.R;
import com.mysql.fabric.xmlrpc.base.Array;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import sun.misc.FpUtils;


/**
 * 本周警情Controller
 * @author liangxiao
 * @date 2018年10月18日 上午10:02:20
 */
@RequestMapping("/jqfx/sys")
@Controller
public class SysController {
	
	@Autowired
	private SysService sysService;
	
	@Autowired 
	HttpServletRequest request;
	
	
		/*@RequestMapping(value = "/batchSave", method = RequestMethod.POST)
		@ResponseBody
		public R batchSave(@RequestParam("file") MultipartFile attachment,HttpServletRequest request) 	*/
	
	/**
	 * 创建表
	 * @param sys
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createNewTable", method = RequestMethod.POST)
	
	public R createNewTable(Sys sys,@RequestParam(value="excelFile") MultipartFile file){
		 Map<String, Object> table = new HashMap<>();
		 Map<String, Object> tableComment = new HashMap<>();
		 Map<String, Object> fieldComment = null;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 UserDO user = (UserDO) request.getSession().getAttribute("user");
		 
		
		try {
			StringBuffer field = new StringBuffer();
			// Workbook workbook = getWorkBook(file);  
			Workbook wb = Workbook.getWorkbook(file.getInputStream());
			Sheet sheet = wb.getSheet(0); 
					Cell[] row = sheet.getRow(0);
					Cell[] row2 = sheet.getRow(1);
					if(row.length>0){
						field.append("ID VARCHAR2(64 BYTE) DEFAULT sys_guid() NOT NULL,");
					}
					for (int j = 0; j <row2.length; j++) {						
						field.append(row2[j].getContents().toUpperCase()+" VARCHAR2(100 BYTE) ");
						if(j!=row2.length-1){
							field.append(",");
						}
					}
					String name = null;
					if(sysService.getZjTableCount()==null){
						name = "TABLE1";
					}else{
						 name = "TABLE"+(Integer.parseInt(sysService.getZjTableCount())+1);
					}
					//创建表
					table.put("tableName", name);
					table.put("field", field.toString());
					sysService.createNewTable(table);
					//表注释
					tableComment.put("tableName", name);
					tableComment.put("comment", sys.getTablename());
					sysService.updateTableComment(tableComment);
					//字段注释
					for(int i = 0; i < row.length; i++){
						fieldComment = new HashMap<>();
						fieldComment.put("field", name+"."+row2[i].getContents().toUpperCase());
						fieldComment.put("comment", row[i].getContents());
						sysService.updateFieldComment(fieldComment);
					}	
					Map<String, Object>  fieldComment2 = new HashMap<>();
					fieldComment2.put("field", name+".ID");
					fieldComment2.put("comment", "ID");
					
					TableResource tableResource = new TableResource();
					tableResource.setTablecomments(sys.getTablename());
					tableResource.setTablename(name);
					tableResource.setUsername(user.getUsername());
					tableResource.setCreatetime(sdf.format(new Date()));
					int i = sysService.updateFieldComment(fieldComment2);
					if(i>0) {
						sysService.saveTable(tableResource);
					}
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return R.ok();
	}
	/*@ResponseBody
	@RequestMapping(value = "/createNewTable", method = RequestMethod.POST)
	
	public R createNewTable(Sys sys,@RequestParam(value="excelFile") MultipartFile file){
		 Map<String, Object> table = new HashMap<>();
		 Map<String, Object> tableComment = new HashMap<>();
		 Map<String, Object> fieldComment = null;
		 System.out.println(file.getOriginalFilename());
		// int s = file.getOriginalFilename().indexOf("."); 
		//System.out.println(s);
		 File  file = renameFile(file.getOriginalFilename(), file.getOriginalFilename().substring(0, s)+"xls");
		
		 System.out.println(file.getOriginalFilename().substring(0, s)+"xls");
		try {
			StringBuffer field = new StringBuffer();
			 //获得Workbook工作薄对象  
	        Workbook workbook = getWorkBook(file);  
	        System.out.println(workbook.getNumberOfSheets());
			//Workbook wb = Workbook.getWorkbook(file.getInputStream());
	        Sheet sheet = workbook.getSheetAt(0);  
	        	Row row = sheet.getRow(0);
	        	Row row2 = sheet.getRow(1);
					if(row.getPhysicalNumberOfCells()>0){
						field.append("ID VARCHAR2(64 BYTE) DEFAULT sys_guid() NOT NULL,");
					}
					for (int j = 0; j <row2.getPhysicalNumberOfCells(); j++) {	
						Cell cell = row2.getCell(j);  
                       // cells[cellNum] = getCellValue(cell);  
						field.append(cell.toString().toUpperCase()+" VARCHAR2(100 BYTE) ");
						if(j!=row2.getPhysicalNumberOfCells()-1){
							field.append(",");
						}
					}
					String name = null;
					if(sysService.getZjTableCount()==null){
						name = "TABLE1";
					}else{
						 name = "TABLE"+(Integer.parseInt(sysService.getZjTableCount())+1);
					}
					//创建表
					table.put("tableName", name);
					table.put("field", field.toString());
					sysService.createNewTable(table);
					//表注释
					tableComment.put("tableName", name);
					tableComment.put("comment", sys.getTablename());
					sysService.updateTableComment(tableComment);
					//字段注释
					for(int i = 0; i < row.getPhysicalNumberOfCells(); i++){
						Cell cell = row.getCell(i);
						Cell cell2 = row2.getCell(i);  
						fieldComment = new HashMap<>();
						fieldComment.put("field", name+"."+cell2.toString().toUpperCase());
						fieldComment.put("comment", cell.toString());
						sysService.updateFieldComment(fieldComment);
					}	
					Map<String, Object>  fieldComment2 = new HashMap<>();
					fieldComment2.put("field", name+".ID");
					fieldComment2.put("comment", "ID");
					sysService.updateFieldComment(fieldComment2);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return R.ok();
	}*/
	/*public static Workbook getWorkBook(MultipartFile file) {  
        //获得文件名  
        String fileName = file.getOriginalFilename();  
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;  
        try {  
            //获取excel文件的io流  
            InputStream is = file.getInputStream();  
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if(fileName.endsWith("xls")){  
                //2003  
                workbook = new HSSFWorkbook(is);  
            }else if(fileName.endsWith("xlsx")){  
                //2007  
                workbook = new XSSFWorkbook(is);  
            }  
        } catch (IOException e) {  
           // logger.info(e.getMessage());  
        }  
        return workbook;  
    }  */
	
	/* public static String getCellValue(Cell cell){  
	        String cellValue = "";  
	        if(cell == null){  
	            return cellValue;  
	        }  
	       System.out.println(cell.getCellType());
	      
	        return cellValue;  
	    }*/  
	/**
	 * 模板下载
	 * <p>TODO</p>
	 * <p>@author </p>
	 * <p>@date 2019年1月10日 下午3:27:27</p>
	 * @param request
	 * @param response void
	 */
	@RequestMapping("/downLoadTableMb")
	public void downLoadFieldMb(HttpServletRequest request,HttpServletResponse response){
		//下载文件路径
	
		String saveFileName = "Mb.xls";
		String path = request.getServletContext().getRealPath(File.separator + "style/download/Mb.xls");
		//System.out.println(path);
		response.setContentType("application/ostet-stream");
		OutputStream outp = null;
		FileInputStream in = null;
		try {
			response.addHeader("Content-Disposition","attachment;filename=\""+ encodeFilename(request, saveFileName)
					+ "\"");// 名称两边的双引号不能省略 兼容火狐 文件名中的空格
			outp = response.getOutputStream();
			in = new FileInputStream(path);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) > 0) {
				outp.write(b, 0, i);
			}
			outp.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
			if (outp != null) {
				try {
					outp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				outp = null;
			}
		}
     
	}
	
	private static String encodeFilename(HttpServletRequest request,
			String fileName) throws UnsupportedEncodingException {
		String agent = request.getHeader("USER-AGENT");

		try {
			// IE
			if (null != agent && -1 != agent.indexOf("MSIE")) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
				// Firefox
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			try {
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return fileName;
	}
	/**
	 * 获取数据库中的所有的表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getTable")
	public PageUtils getTable(@RequestParam Map<String, Object> params) {
		
		List<Sys> list = null;
		try {
			list = sysService.getTable(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int total = sysService.count();
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	/**
	 * 获取表中的所有字段
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getFields")
	public PageUtils getFields(@RequestParam Map<String, Object> params) {
		
		List<Fields> list = null;
		int total = 0;
		try {
			list = sysService.getFields(params);
			total = sysService.countFields(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	/**
	 * 下载上传数据的模板
	 * @param params
	 * @param response
	 * @return
	 */
	@ResponseBody
	@GetMapping("/downLoadFieldMb")
	public R downLoadFieldMb(@RequestParam Map<String, Object> params, HttpServletResponse response){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		OutputStream out = null;
		FileInputStream in = null;
		try {
			List<Fields> list = sysService.getFieldNoId(params);
			File file = new File("Fileld-"+sdf.format(new Date())+".xls");
			//String downLoadFileName = URLEncoder.encode("Fileld.xlx", "UTF-8");
			WritableWorkbook book = Workbook.createWorkbook(file);
			WritableSheet sheet = book.createSheet("sheet", 0);
			for (int i = 0; i < list.size(); i++) {
				
					 sheet.addCell(new Label(i, 0, list.get(i).getComments()));
						sheet.addCell(new Label(i, 1, list.get(i).getColumnname()));
				
				
			}
			book.write();
			book.close();
			
			String fileName = "attachment; filename=\"Fileld-"+sdf.format(new Date())+".xls\"";
			
			
			response.reset();
			response.setHeader("Content-Disposition", fileName);
			response.setContentType("application/x-msdownload");
			in = new FileInputStream(file);
			if (book != null) {
				out = response.getOutputStream();
				
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = in.read(b)) > 0) {
					out.write(b, 0, i);
				}
				out.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
			if (out!= null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				out = null;
			}
		}
		return R.ok();
		
		
	}
	
	/**
	 * 获取要上传表数据的字段名
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUploadFiled")
	public R getUploadFiled(@RequestParam Map<String, Object> params){
		List<Fields> list = null;
		 StringBuffer field = new StringBuffer();
		try {
			list = sysService.getFieldNoId(params);
			for (int i = 0; i < list.size(); i++) {
				field.append(list.get(i).getComments());
				if(i!=(list.size()-1)){
					field.append(",");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return R.ok().put("data",field.toString());
	}
	
	/**
	 * 上传表数据插入到数据库中
	 * @param sys
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadFieldDb", method = RequestMethod.POST)
	//@GetMapping("/createNewTable")
	public R uploadFieldDb(Sys sys,@RequestParam(value="excelFile") MultipartFile file){
		List<String> list = new ArrayList<>();
		 Map<String, Object> fieldComment =new HashMap<>();
		 StringBuffer field = new StringBuffer();
	
		try {
			
			Workbook wb = Workbook.getWorkbook(file.getInputStream());
			Sheet sheet = wb.getSheet(0);
			//字段名称
			Cell[] rowField = sheet.getRow(1);
			for (int j = 0; j <rowField.length; j++) {
				field.append(rowField[j].getContents().toUpperCase());
				if(j!=(rowField.length-1)){
					field.append(",");
				}
			}
			for (int i = 2; i < sheet.getRows(); i++) {
				Cell[] rowValue = sheet.getRow(i);
				//StringBuffer value = new StringBuffer();
				StringBuffer values = new StringBuffer();
				for (int n = 0; n < rowValue.length; n++) {
					//value.append("'"+rowValue[n].getContents().toUpperCase()+"'");
					values.append(rowValue[n].getContents().toUpperCase());
					if(n!=(rowValue.length-1)){
						//value.append(",");
						values.append(",");
					}
				}
				list.add(values.toString());
				/*fieldComment.put("tablename", sys.getTablename());	
				fieldComment.put("field", field.toString());	
				fieldComment.put("value", value.toString());	
				sysService.save(fieldComment);	*/	
			}
					
			
					
					
					
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return R.ok().put("data",list);
	}
	
	/**
	 * 上传表数据插入到数据库中
	 * @param sys
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadFields", method = RequestMethod.POST)
	//@GetMapping("/createNewTable")
	public R uploadFields(Sys sys,@RequestParam(value="excelFile") MultipartFile file){
		List<String> list = new ArrayList<>();
		 Map<String, Object> fieldComment =new HashMap<>();
		 StringBuffer field = new StringBuffer();
	
		try {
			
			Workbook wb = Workbook.getWorkbook(file.getInputStream());
			Sheet sheet = wb.getSheet(0);
			//字段名称
			Cell[] rowField = sheet.getRow(1);
			for (int j = 0; j <rowField.length; j++) {
				field.append(rowField[j].getContents().toUpperCase());
				if(j!=(rowField.length-1)){
					field.append(",");
				}
			}
			for (int i = 2; i < sheet.getRows(); i++) {
				Cell[] rowValue = sheet.getRow(i);
				StringBuffer value = new StringBuffer();
				//StringBuffer values = new StringBuffer();
				for (int n = 0; n < rowValue.length; n++) {
					value.append("'"+rowValue[n].getContents().toUpperCase()+"'");
				//	values.append(rowValue[n].getContents().toUpperCase());
					if(n!=(rowValue.length-1)){
						value.append(",");
					//	values.append(",");
					}
				}
				//list.add(values.toString());
				fieldComment.put("tablename", sys.getTablename());	
				fieldComment.put("field", field.toString());	
				fieldComment.put("value", value.toString());	
				sysService.save(fieldComment);		
			}
					
			
					
					
					
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return R.ok();
	}
	

	/**
	 * 修改字段
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateField")
	public R updateField(@RequestParam Map<String, Object> params) {
		//R r = new R();
		String str = null;
		try {
			//字段名
			String field = params.get("field").toString();
			//字段中文名
			String fieldComment = URLDecoder.decode(params.get("fieldComment").toString(), "UTF-8");
			//表名
			String table = params.get("table").toString();
			//字段类型
			String type = params.get("type").toString();
			
			//判断字段中文名是否有重复
			Map<String, Object> commentExit = new HashMap<>();
			commentExit.put("tablename", table.toUpperCase());
			commentExit.put("comment", fieldComment);
			//int count = sysService.countComments(commentExit);
			
				//修改字段中文备注
				Map<String, Object> comment = new HashMap<>();
				comment.put("field", table.toUpperCase()+"."+field.toUpperCase());
				comment.put("comment",  fieldComment);
				sysService.updateFieldComment(comment);
				
				//修改字段类型
				Map<String, Object> fieldType = new HashMap<>();
				fieldType.put("tablename", table.toUpperCase());
				fieldType.put("field", field.toUpperCase()+" "+type);			
				sysService.updateFieldType(fieldType);
				
				str = "2";
			
			
		} catch (Exception e) {
			return R.error(); 
		}
		//return r;
		return R.ok(str);
	}
	
	
	/**
	 * 添加字段
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addField")
	public R addField(@RequestParam Map<String, Object> params) {
		String str = null;
		try {
			
			//字段中文名
			String fieldComment = URLDecoder.decode(params.get("fieldComment").toString(), "UTF-8");
			//表名
			String table = params.get("table").toString();
			//字段类型
			String type = params.get("type").toString();
			
			//判断字段中文名是否有重复
			Map<String, Object> commentExit = new HashMap<>();
			commentExit.put("tablename", table.toUpperCase());
			commentExit.put("comment", fieldComment);
			int count = sysService.countComments(commentExit);
			if(count>0) {
				str = "1";
				 //r.error("改字段名已存在");
			}else {
				
				String name = null;
				if(sysService.getZjFieldCount(params)==null){
					name = "FIELD1";
				}else{
					 name = "FIELD"+(Integer.parseInt(sysService.getZjFieldCount(params))+1);
				}
				//添加字段
				Map<String, Object> fields = new HashMap<>();
				fields.put("tablename", table.toUpperCase());
				fields.put("field", name+" "+type);			
				sysService.addField(fields);
				
				//修改字段中文备注
				Map<String, Object> comment = new HashMap<>();
				comment.put("field", table.toUpperCase()+"."+name.toUpperCase());
				comment.put("comment", fieldComment);
				sysService.updateFieldComment(comment);
				str = "2";
			}
			
		} catch (Exception e) {
			return R.error(); 
		}
		//return r;
		return R.ok(str);
	}
	
	/**
	 * 删除字段
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteField")
	public R deleteField(@RequestParam Map<String, Object> params) {
		try {
			sysService.deleteField(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return R.ok();
	}
	
	
	 
	   /* public static void main(String[] args) {
	        new Main().renameFile("C:\\temp\\file1.txt", "C:\\temp\\file2.txt");
	    }*/



}
