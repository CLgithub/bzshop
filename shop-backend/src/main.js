import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import "./assets/css/reset.css"
import api from "./api"
// import "./router/Permission"
import Storage from "./utils/Storage"
import VueUeditorWrap from 'vue-ueditor-wrap'

Vue.config.productionTip = false

Vue.component('vue-ueditor-wrap', VueUeditorWrap)
Vue.prototype.$api = api;

// 重新赋值
if(Storage.getItem("token")){
  store.commit("setToken",Storage.getItem("token"));
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
