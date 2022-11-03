package com.cl.item.controller;

import com.cl.gzshop.utils.PageResult;
import com.cl.item.service.ItemService;
import com.cl.pojo.TbItem;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

/**
 * @Author l
 * @Date 2020/9/21 20:24
 */
@RestController
@RequestMapping("/service/item")
public class ItemController {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private ItemService itemService;

    @RequestMapping("/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(@RequestParam Integer page, @RequestParam Integer rows){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostName = addr.getHostName();
            logger.info(System.currentTimeMillis()+"-"+hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return itemService.selectTbItemAllByPage(page,rows);
    }

    /**
     * 商品添加
     */
    @RequestMapping("/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem){    //  参数用的是自定义对象，要用@RequestBody注解
        return this.itemService.insertTbItem(tbItem);
    }

    /**
     * 删除商品
     * @param tbItem
     * @return
     */
    @RequestMapping("/deleteItemById")
    public Integer deleteItemById(@RequestBody TbItem tbItem){
        return this.itemService.updateItemById(tbItem);
    }

    /**
     * 根据商品id查询，商品、商品描述、商品分类
     * @param itemId
     * @return
     */
    @RequestMapping("/preUpdateItem")
    public Map<String, Object> preUpdateItem(@RequestParam Long itemId){
        return this.itemService.preUpdateItem(itemId);
    }

    /**
     * 对商品表的更新
     * @param tbItem
     * @return
     */
    @RequestMapping("/updateItemById")
    public Integer updateItemById(@RequestBody TbItem tbItem){
        return this.itemService.updateItemById(tbItem);
    }

    /**
     * 根据商品ID查询商品
     * @param itemId
     * @return
     */
    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(@RequestParam Long itemId){
        return itemService.selectItemInfo(itemId);
    }

}
