import { jQuery as $ } from '../webjars/jquery/3.2.1/jquery.min.js';

export class Dialog extends HTMLElement {
    
    static _getTemplate() {
        const result = document.createElement('template');
        result.innerHTML = `
            <link rel="stylesheet" href="./element/dialog.css">
            <div id="window">
                <div id="title"><slot name="title">No Title</slot></div>
                <div id="content"><slot>No Content</slot></div>
                <div id="button-bar"><slot name="button-bar"></slot></div>
            </div>
        `;
        return result;
    }
   
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        this.shadowRoot.appendChild(Dialog._getTemplate().content.cloneNode(true));
    }
    
    connectedCallback() {
        $(this.shadowRoot.host).hide();
    }
    
    show() {
        $(this.shadowRoot.host).show();
    }
    
    hide() {
        $(this.shadowRoot.host).hide();
    }
	
	set width(width) {
		this.shadowRoot.host.style.setProperty('--dialog-width', width + 'px');
	}

	set height(height) {
		this.shadowRoot.host.style.setProperty('--dialog-height', height + 'px');
	}	
    
    setButtonBar(buttonBarEl) {
        buttonBarEl.setAttribute('slot', 'button-bar');
        this.appendChild(buttonBarEl);
    }
    
    setContent(contentEl) {
        this.appendChild(contentEl);
    }
}
