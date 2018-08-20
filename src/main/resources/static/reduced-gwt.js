import { createNamespace, getFrameworkMethods } from './framework/framework.js';

import { Dialog } from './element/dialog.js';
import { Grid } from './element/grid.js';
import { TileButton } from './element/tile-button.js';

createNamespace('$application-package$' + '.client').JsBridge = class {

    constructor() {
    }

    static init(applicationId) {
        customElements.define(applicationId + '-dialog', Dialog);
        customElements.define(applicationId + '-grid', Grid);
        customElements.define(applicationId + '-tile-button', TileButton);
        
        return getFrameworkMethods();
    }
};
