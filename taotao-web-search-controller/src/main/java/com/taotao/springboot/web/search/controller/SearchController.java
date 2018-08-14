package com.taotao.springboot.web.search.controller;

import com.taotao.springboot.search.domain.result.SearchRes;
import com.taotao.springboot.search.export.SearchResource;
import com.taotao.springboot.web.search.common.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Title: SearchController</p>
 * <p>Description: 商品搜索Controller</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-06 15:18</p>
 * @author ChengTengfei
 * @version 1.0
 */
@Controller
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchResource searchResource;

    @Value("${SEARCH_RESULT_ROWS}")
    private Integer SEARCH_RESULT_ROWS;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString,
                         @RequestParam(defaultValue="1") Integer page, Model model) throws Exception {
        // 把查询条件进行转码，解决get乱码问题
        //queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
        // 调用服务执行查询
        log.info("商品搜索 queryString={} and page={}", queryString, String.valueOf(page));
        SearchRes searchRes = searchResource.search(queryString, page, SEARCH_RESULT_ROWS);
        log.info("商品搜索 res={}", JacksonUtils.objectToJson(searchRes));
        // 把结果传递给页面
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchRes.getTotalPages());
        model.addAttribute("itemList", searchRes.getItemList());
        model.addAttribute("page", page);
        // 返回逻辑视图
        return "search";
    }

}