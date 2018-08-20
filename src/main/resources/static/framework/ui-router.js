import { UIRouter } from "../webjars/ui-router-core/5.0.9/router.js";
import { servicesPlugin, hashLocationPlugin } from "../webjars/ui-router-core/5.0.9/vanilla/plugins.js";

export function intializeUiRouter(states, defaultState) {
    const uiRouter = new UIRouter();
    servicesPlugin(uiRouter);
    uiRouter.plugin(hashLocationPlugin);

    states.forEach(s => uiRouter.stateRegistry.register(s));
    // NOTE Removing trailing slashes.
    uiRouter.urlRouter.when((url, router) => {
        const parts = url.path.split('/');
        const last = parts.reverse().find((part) => { return part.length; });
        if(last && last.indexOf('.') === -1 && url.path[url.path.length - 1] === '/') {
            return true;
        }
    }, (matchValue, url, router) => {
        location.hash = url.path.substring(0, url.path.length - 1);
    });    
    uiRouter.urlRouter.otherwise((matchValue, url, router) => {
        location.hash = router.stateService.href(defaultState);
        return;
    });
    uiRouter.urlService.listen();
    uiRouter.urlService.sync();
    return uiRouter;
}