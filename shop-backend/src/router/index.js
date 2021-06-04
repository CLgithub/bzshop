import Vue from 'vue'
import Router from 'vue-router'
import Product from '../pages/Product/Product.vue'
import Params from "../pages/Params/Params.vue"
import ContentCategory from "../pages/ContentCategory/ContentCategory.vue"
import Login from "../pages/Login/Login.vue"

Vue.use(Router)

export default new Router({
  mode: 'history',
  linkActiveClass: "active",
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'Product',
      component: Product,
      meta: {
        isLogin: true
      }
    },
    {
      path: '/params',
      name: 'Params',
      component: Params,
      meta: {
        isLogin: true
      }
    },
    {
      path: '/contentcategory',
      name: 'ContentCategory',
      component: ContentCategory,
      meta: {
        isLogin: true
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
