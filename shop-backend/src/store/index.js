import Vue from 'vue'
// import 'es6-promise/auto'
import Vuex from 'vuex'
import defaultState from "./defaultState"
import mutations from "./mutations"
import actions from "./actions"
import getters from "./getters"
import createLogger from 'vuex/dist/logger'

/**
 * 为了程序的分离性更好，或则不再臃肿，我们可以增加moduleA和moduleB模块
 */

Vue.use(Vuex)

// 创建插件
const myPlugin = store => {
    store.subscribe((mutation,state) => {
        // 生成日志信息：每一次的用户操作的日志信息
    })
}

const store = new Vuex.Store({
    // 开启严格模式
    strict: process.env.NODE_ENV !== 'production',
    state: defaultState,
    mutations,
    actions,
    getters
    // plugins: [myPlugin,createLogger()]   // 内置 Logger 插件
})

// 添加热重载
if(module.hot){
    // 使 action,getters,state 和 mutation 成为可热重载模块
    module.hot.accept(["./defaultState","./mutations","./actions","./getters"], () =>{
        // 获取更新后的模块
        const newState = require('./defaultState').default
        const newMutations = require('./mutations').default
        const newActions = require('./actions').default
        const newGetters = require('./getters').default
        // 加载新模块
        store.hotUpdate({
            state:newState,
            mutations:newMutations,
            actions:newActions,
            getters:newGetters
        })
    })
}



export default store