import { intializeUiRouter }from './ui-router.js';

export function createNamespace(namespace) {
    const parts = namespace.split('\.');
    let current = window;
    for (const part of parts) {
        current[part] = current[part] || {};
        current = current[part];
    }
    return current;
}

class JsArrayHelper {
    
    static createArray() {
        return [];
    }
    
    static getArrayValue(a, i) {
		return a[i];
	}    
};

class UiRouter {
    
    static initialize(states) {
        for (const currentState of states.states) {
            currentState.onEnter = (transition, state) => {
                const href = transition.router.stateService.href(state.name, transition.params());
                const isTarget = (transition.to().name === state.name);
                const promise = currentState.onEnterCallback(transition.params('to')[currentState.paramName], href, isTarget);
                return promise;
            };
            currentState.onExit = (transition, state) => {
                const href = transition.router.stateService.href(state.name, transition.params());
                const isSubTreeChange = !!!state.$$state().path.find((node) => { return node.name === transition.to().name; });
                currentState.onExitCallback(transition.params('to')[currentState.paramName], href, isSubTreeChange);
            };
        }
        
        UiRouter.instance = intializeUiRouter(states.states, states.defaultState);
    }   
    
    static getHrefMethod(stateName, params) {
        const href = UiRouter.instance.stateService.href(stateName, params);
        return href;
    }
};

export function getFrameworkMethods() {
    return {
        uiRouterInitializeMethod: UiRouter.initialize,
        uiRouterGetHrefMethod: UiRouter.getHrefMethod,
        
        jsArrayHelperCreateArrayMethod : JsArrayHelper.createArray,
        jsArrayHelperGetArrayValueMethod : JsArrayHelper.getArrayValue
    };
}