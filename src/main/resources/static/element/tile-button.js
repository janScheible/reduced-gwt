import { jQuery as $ } from '../webjars/jquery/3.2.1/jquery.min.js';

export class TileButton extends HTMLElement {
    
    static _getTemplate() {
        const result = document.createElement('template');
        result.innerHTML = `
            <link rel="stylesheet" href="./element/tile-button.css">
            <a id="link" href="#"><span><slot>Tile Button</slot></span></a>
        `;
        return result;
    }
   
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        this.shadowRoot.appendChild(TileButton._getTemplate().content.cloneNode(true));
        this._linkEl = $(this.shadowRoot).find('#link');
    }
    
    connectedCallback() {
        if(this.getAttribute('href')) {
            this._linkEl.attr('href', this.getAttribute('href'));
        }
    }
    
    set href(href) {
        if (href) {
            this.setAttribute('href', href);
        } else {
            this.removeAttribute('href');
        }
        this._linkEl.attr('href', href);
    }

    set text(text) {
        $(this).text(text);
    }

    set backgroundColor(color) {
        this.shadowRoot.host.style.setProperty('--tile-button-bg-color', color);
    }
    
    set fontColor(color) {
        this.shadowRoot.host.style.setProperty('--tile-button-font-color', color);
    }
}
