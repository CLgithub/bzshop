export function setParams(params,paramsValues){
    let finalStr = [];
    let currrentObj = {};
    for(let i = 0 ;i<params.length;i++){
        let currentTitle = params[i].value;
        for(let j = 0;j<params[i].children.length;j++){
            currrentObj = {'value':currentTitle,'children':[{"childValue":params[i].children[j].childValue,'value':paramsValues[params[i].children[j].childValue]}]}
        }
        finalStr.push(currrentObj)
    }
    console.log(finalStr);
    
    return JSON.stringify(finalStr);
}