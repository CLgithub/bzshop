export default {
    setToken(state,tokenValue){
        state.token = tokenValue
    },
    delToken(state){
        state.token = null;
    }
}