/**
 * 封装axios
 */

import axios from "axios"
import qs from "querystring"
import router from "../router"
import store from "../store"

const toLogin = () =>{
    router.push("/login")
}

const errorHandle = (status,other) => {
    switch(status){
        case 400:
            console.log("请求信息发生错误");
            break;
        case 401:
            console.log("用户信息验证失败:token验证失败");
            localStorage.removeItem("token")
            toLogin();
            break;
        case 403:
            console.log("请求被限制");
            localStorage.removeItem("token")
            toLogin();
            break;
        case 404:
            console.log("客户端错误");
            break;
        default:
            console.log(other);
            break;
    }
}

const instance = axios.create({ timeout: 5000 })

/**
 * 请求和响应拦截
 */

instance.interceptors.request.use(
    config => {
        if (config.method === "post") {
            config.data = qs.stringify(config.data);
        }
        if(store.state.token){
            config.headers.authorization = store.state.token
        }
        return config;
    },
    error => Promise.reject(error)
)

instance.interceptors.response.use(
    response => response.status === 200 ? Promise.resolve(response) : Promise.reject(response),
    error => {
        const { response } = error;
        if (response) {
            //  处理错误信息
            errorHandle(response.status,response.data);
            return Promise.reject(response);
        }else{
            // 断网操作，请求发生错误
            console.log("断网操作，请求发生错误");
        }
    }
)

export default instance;