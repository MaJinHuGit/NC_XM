package com.mtlckj.base.jqfx.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtlckj.base.jqfx.domain.Wffzjq;
import com.mtlckj.base.jqfx.service.BzjqService;
import com.mtlckj.base.system.utils.PageUtils;
import com.mtlckj.base.system.utils.R;


/**
 * 本周警情Controller
 * @author liangxiao
 * @date 2018年10月18日 上午10:02:20
 */
@RequestMapping("/jqfx/bzjq")
@Controller
public class BzjqController {
	
	@Autowired
	private BzjqService bzjqService;
	
	
	/**
	 * 本周警情统计
	 * @param params
	 * @return 
	 */
	@ResponseBody
	@GetMapping("/countByWeek")
	public R countByWeek(@RequestParam Map<String, Object> params) {
		Map<String, Object>  wffzcunt = bzjqService.getWffzCount(params);
		Map<String, Object> map = bzjqService.countByWeek(params);
		Map<String, Object> cunt = new HashMap<String, Object>();
		String wffzcuntbq = wffzcunt.get("bq").toString();
		String wffzcuntsq = wffzcunt.get("sq").toString();
		String wffzcunttq = wffzcunt.get("tq").toString();
		Map<String, Object> zjq = (Map<String, Object>) map.get("zjq");
		String mapbq = zjq.get("bq").toString();
		String mapsq = zjq.get("sq").toString();
		String maptq = zjq.get("tq").toString();
		if("".equals(wffzcuntbq)) {
			wffzcuntbq = "0";
		}
		if("".equals(wffzcuntsq)) {
			wffzcuntsq = "0";
		}
		if("".equals(wffzcunttq)) {
			wffzcunttq = "0";
		}
		if("".equals(mapbq)) {
			mapbq = "0";
		}
		if("".equals(mapsq)) {
			mapsq = "0";
		}
		if("".equals(maptq)) {
			maptq = "0";
		}

		cunt.put("bq", Integer.parseInt(wffzcuntbq)+Integer.parseInt(mapbq));//本期
		cunt.put("sq", Integer.parseInt(wffzcuntsq)+Integer.parseInt(mapsq));//上期
		cunt.put("tq", Integer.parseInt(wffzcunttq)+Integer.parseInt(maptq));
		cunt.put("hb", getTbHb( Integer.parseInt(wffzcuntsq)+Integer.parseInt(mapsq),Integer.parseInt(wffzcuntbq)+Integer.parseInt(mapbq)));//环比
		cunt.put("tb",getTbHb(Integer.parseInt(wffzcunttq)+Integer.parseInt(maptq),Integer.parseInt(wffzcuntbq)+Integer.parseInt(mapbq)));//计算同比
		return R.ok().put("data",map).put("wffzcunt",wffzcunt).put("cunt",cunt);
	}
	// 同比和环比
		private String getTbHb(int lastNumber, int nowNumber) {
			int last = lastNumber;
			int now = nowNumber;
			float tb = now;
			if (last != 0) {
				double i = now - last;
				tb = (float) (i / last);

			}

			NumberFormat nt = NumberFormat.getPercentInstance();
			// 设置百分数保留两位小数
			nt.setMinimumFractionDigits(2);
			// System.out.println("==="+nt.format(tb));
			return nt.format(tb);
		}
	
	@ResponseBody
	@GetMapping("/countByHours")
	public R countByHours(@RequestParam Map<String, Object> params) {
		Map<String, Object> map = bzjqService.countByHours(params);
		return R.ok().put("data",map);
	}
	
	
	/**
	 * 警情列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getList")
	public PageUtils getList(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> list = bzjqService.getList(params);
		int total = bzjqService.count(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/getListAfdz")
	public PageUtils  getListAfdz (@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("name") != null && !"".equals(params.get("name"))) {
				String name = URLDecoder.decode((String) params.get("name"),"UTF-8");
				params.put("name", name);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> list = bzjqService.getListAfdz(params);
		int total = bzjqService.countAfdz(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/getListGjz")
	public PageUtils  getListGjz (@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("name") != null && !"".equals(params.get("name"))) {
				String name = URLDecoder.decode((String) params.get("name"),"UTF-8");
				params.put("name", name);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> list = bzjqService.getListGjz(params);
		int total = bzjqService.countGjz(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/getListGxdw")
	public PageUtils  getListGxdw (@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("name") != null && !"".equals(params.get("name"))) {
				String name = URLDecoder.decode((String) params.get("name"),"UTF-8");
				params.put("name", name);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> list = bzjqService.getListGxdw(params);
		int total = bzjqService.countGxdw(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/getListBjdh")
	public PageUtils getListBjdh(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("name") != null && !"".equals(params.get("name"))) {
				String name = URLDecoder.decode((String) params.get("name"),"UTF-8");
				params.put("name", name);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> list = bzjqService.getListBjdh(params);
		int total = bzjqService.countBjdh(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/getListBjxl")
	public PageUtils getListBjxl(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("name") != null && !"".equals(params.get("name"))) {
				String name = URLDecoder.decode((String) params.get("name"),"UTF-8");
				params.put("name", name);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> list = bzjqService.getListBjxl(params);
		int total = bzjqService.countBjxl(params);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	}
	/**
	 * 警情列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/get")
	public R get(@RequestParam String jjdbh) {
		Wffzjq wffzjq = bzjqService.get(jjdbh);
		return R.ok().put("data",wffzjq);
	}
	
	/**
	 * 案发地点
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getAfdz")
	public PageUtils getAfdz(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> listAfdz = null;
		int total = 0;
		if("交通事故".equals(params.get("lx").toString())||"噪音扰民".equals(params.get("lx").toString())||"火灾".equals(params.get("lx").toString())) {
			listAfdz = bzjqService.getAfdzByLx(params);
			total = bzjqService.getAfdzByLxCount(params);
		}else {
			listAfdz = bzjqService.getAfdz(params);
			total = bzjqService.getAfdzCount(params);
		}
		
		PageUtils pageUtils = new PageUtils(listAfdz, total);
		return pageUtils;
	}
	
	/**
	 * 案发地点
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getGjz")
	public PageUtils getGjz(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> listGjz = null;
		int total = 0;
		if("交通事故".equals(params.get("lx").toString())||"噪音扰民".equals(params.get("lx").toString())||"火灾".equals(params.get("lx").toString())) {
			listGjz = bzjqService.getGjzByLx(params);
			total = bzjqService.getGjzByLxCount(params);
		}else {
			listGjz = bzjqService.getGjz(params);
			total = bzjqService.getGjzCount(params);
		}
		
		PageUtils pageUtils = new PageUtils(listGjz, total);
		return pageUtils;
	}
	
	

	/**
	 * 报警时间
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getBjsj")
	public R getBjsj(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> listBjsj = null;
		if("交通事故".equals(params.get("lx").toString())||"噪音扰民".equals(params.get("lx").toString())||"火灾".equals(params.get("lx").toString())) {
			listBjsj = bzjqService.getBjsjByLx(params);
			
		}else {
			listBjsj = bzjqService.getBjsj(params);
			
		}
		return R.ok().put("data",listBjsj);
	}
	
	/**
	 * 报警系类
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getBjxl")
	public R getBjxl(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> listBjxl = null;
		if("交通事故".equals(params.get("lx").toString())||"噪音扰民".equals(params.get("lx").toString())||"火灾".equals(params.get("lx").toString())) {
			listBjxl = bzjqService.getBjxlByLx(params);
			
		}else {
			listBjxl = bzjqService.getBjxl(params);
			
		}
		return R.ok().put("data",listBjxl);
	}
	
	/**
	 * 管辖单位
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getGxdw")
	public R getGxdw(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> listGxdw = null;
		if("交通事故".equals(params.get("lx").toString())||"噪音扰民".equals(params.get("lx").toString())||"火灾".equals(params.get("lx").toString())) {
			listGxdw = bzjqService.getGxdwByLx(params);
			
		}else {
			listGxdw = bzjqService.getGxdw(params);
			
		}
		return R.ok().put("data",listGxdw);
	}
	
	/**
	 * 报警电话
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getBjdh")
	public PageUtils getBjdh(@RequestParam Map<String, Object> params) {
		try {
			if(params.get("lx") != null && !"".equals(params.get("lx"))) {
				String lx = URLDecoder.decode((String) params.get("lx"),"UTF-8");
				params.put("lx", lx);
		    }
			if(params.get("search") != null && !"".equals(params.get("search"))) {
				String search = URLDecoder.decode((String) params.get("search"),"UTF-8");
				params.put("search", search);
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Wffzjq> listGjz = null;
		int total = 0;
		if("交通事故".equals(params.get("lx").toString())||"噪音扰民".equals(params.get("lx").toString())||"火灾".equals(params.get("lx").toString())) {
			listGjz = bzjqService.getBjdhByLx(params);
			total = bzjqService.getBjdhByLxCount(params);
		}else {
			listGjz = bzjqService.getBjdh(params);
			total = bzjqService.getBjdhCount(params);
		}
		
		PageUtils pageUtils = new PageUtils(listGjz, total);
		return pageUtils;
	}
}
