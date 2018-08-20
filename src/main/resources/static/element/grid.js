import { jQuery as $ } from '../webjars/jquery/3.2.1/jquery.min.js';

export class Grid extends HTMLElement {
    
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        this.shadowRoot.innerHTML = `
            <link rel="stylesheet" href="./element/grid.css">
        `;
        
        this._rowSelectionListeners = [];
        this._idList = [];
    }
    
    connectedCallback() {
    }
    
    disconnectedCallback() {
    }

    set renderers(renderers) {
        this._renderers = renderers;
    }

    setRows(titles, rows) {
        this._idList = [];
        
        const existingTableEl = $(this.shadowRoot).children('table');
        const tableEl = existingTableEl.length ? existingTableEl : $('<table>');
        if(!existingTableEl.length) {
            $(this.shadowRoot).append(tableEl);
        } else {
            tableEl.empty();
        }
        
        const headerRowEl = $('<tr>');
        tableEl.append(headerRowEl);
        for (const title of titles) {
            headerRowEl.append($('<th>').text(title));
        }
        
        let foundSelectedRow = !(!!this._selectedId);
        for (const row of rows) {
            const rowEl = $('<tr>').click((event) => this._rowClickHandler(event));
            tableEl.append(rowEl);
            
            let columnIndex = -1;
            for(const cell of row) {
                columnIndex++;
                rowEl.append($('<td>').text(this._renderers[columnIndex](cell)));
            }
            
            const id = row[0];
            this._idList.push(id);
            if(id === this._selectedId) {
                foundSelectedRow = true;
                rowEl.css('background-color', 'gold');
            }
        }
        
        if(!foundSelectedRow) {
            for (const listener of this._rowSelectionListeners) {
                listener(undefined);
            }
        }
    }
    
    _rowClickHandler(event) {
        const rowEl = $(event.currentTarget);
        let id = this._idList[rowEl.index() - 1];
        this._selectedId = id;
        
        rowEl.siblings().css('background-color', '');
        rowEl.css('background-color', 'gold');
        
        for (const listener of this._rowSelectionListeners) {
            listener(id);
        }
    }
    
    addRowSelectionListener(listener) {
        this._rowSelectionListeners.push(listener);
    }
    
    set selected(id) {
        let foundSelectedRow = false;
        if(!id) {
            $(this.shadowRoot).find('tr').css('background-color', '');
            foundSelectedRow = true;
        } else {
            let index = 0; // NOTE Include the header.
            for (const currentId of this._idList) {
                index++;
                if(id === currentId) {
                    foundSelectedRow = true;
                    const rowEl = $($(this.shadowRoot).find('tr')[index]);
                   
                    rowEl.siblings().css('background-color', '');
                    rowEl.css('background-color', 'gold');
        
                    break;
                }
            }
            
            if(!foundSelectedRow) {
                $(this.shadowRoot).find('tr').css('background-color', '');
            }
        }

        this._selectedId = foundSelectedRow ? id : undefined;
    }
    
    get selected() {
        return this._selectedId;
    }
}
