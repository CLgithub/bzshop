import axios from "../utils/http"
import base from "./base"

const api = {
    /**
     * 登陆接口
     */
    getLogin(params){
        return axios.post(base.baseUrl+base.login,params);
    },
    /**
     * 商品列表接口
     *     page:页码  可选
     */
    prodcutList(params){
        return axios.get(base.basePrductUrl+base.prodcutList,{
            params:params
        })
    },
    /**
     * 商品类目选择接口
     *     id:类目id
     */
    getSelectItemCategoryByParentId(params){
        return axios.post(base.basePrductUrl + base.selectItemCategoryByParentId,params)
    },
    /**
     * 添加商品接口
     *  title...
     */
    insertTbItem(params){
        return axios.get(base.basePrductUrl + base.insertTbItem,{
            params:params
        })
    },
    /**
     * 添加规格参数接口
     */
    getGroupParamData(params){
        return axios.get(base.basePrductUrl+base.groupParamData+"/"+params.id);
    },
    /**
     * 商品删除接口
     *     参数：itemId
     */
    getDeleteItemById(params){
        return axios.post(base.basePrductUrl+base.deleteItemById,params);
    },
    /**
     * 预更新商品接口
     *     参数：itemId
     */
    getPreUpdateItem(params){
        return axios.post(base.basePrductUrl + base.preUpdateItem,params);
    },
    /**
     * 修改商品信息接口
     */
    getUpdateProduct(params){
        return axios.get(base.basePrductUrl + base.updateProduct, {
            params: params
        })
    },
    /**
     * 规格参数  查询
     */
    getSelectItemParamAll(){
        return axios.get(base.basePrductUrl +  base.selectItemParamAll);
    },
    /**
     * 规格参数  添加
     * itemCatId:分类的id
     * paramData：分类规格参数
     */
    getInsertItemParam(params){
        return axios.post(base.basePrductUrl + base.insertItemParam,params);
    },
    /**
     * 规格参数  删除
     *  id:当前规格参数id
     * 
     */
    getDeleteItemParamById(params){
        return axios.get(base.basePrductUrl + base.deleteItemParamById,{
            params:params
        })
    },
    /**
     * 分类内容管理 查询接口
     *  
     */
    getSelectContentCategoryByParentId(params){
        return axios.post(base.baseContentUrl + base.selectContentCategoryByParentId,params);
    },
    /**
     * 内容分离管理 添加接口  
     *   parentId:父级id
     *   name:当前要添加的名称
     */
    getInsertContentCategory(params){
        return axios.post(base.baseContentUrl + base.insertContentCategory,params);
    },
    /**
     * 内容分类管理  删除接口
     *    categoryId:当前要删除数据的id
     */
    getDeleteContentCategoryById(params){
        return axios.post(base.baseContentUrl + base.deleteContentCategoryById,params);
    },
    /**
     * 内容分类管理  修改接口
     *     id:当前要修改数据的id
     *     name:修改的name名称
     */
    getUpdateContentCategory(params){
        return axios.post(base.baseContentUrl + base.updateContentCategory,params)
    },
    /**
     * 内容管理  查询接口
     *     categoryId:添加到那一条中
     */
    getSelectTbContentAllByCategoryId(params){
        return axios.post(base.baseContentUrl + base.selectTbContentAllByCategoryId,params);
    },
    /**
     * 内容管理  添加接口
     *     categoryId: 当前要添加内容的分类,
     *     title: 标题,
     *     subTitle: 子标题,
     *     titleDesc: 标题描述,
     *     url: url地址,
     *     pic: 图片,
     *     content: 内容描述               
     */
    getInsertTbContent(params){
        return axios.post(base.baseContentUrl + base.insertTbContent,params);
    },
    /**
     * 内容管理  删除接口
     *  ids:当前删除数据的id
     */
    getDeleteContentByIds(params){
        return axios.post(base.baseContentUrl + base.deleteContentByIds,params);
    }
}

export default api