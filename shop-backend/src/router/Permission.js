import router from "./index"
import store from "../store"

// 权限验证
router.beforeEach((to,from,next)=>{
    if(to.meta.isLogin){
        // 需要登陆  判断是否登陆
        const token = store.state.token;
        if(token){
            next()
        }else{
            next({
                path:"/login"
            })
        }
    }else{
        next();
    }
})