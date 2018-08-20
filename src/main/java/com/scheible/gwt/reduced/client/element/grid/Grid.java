package com.scheible.gwt.reduced.client.element.grid;

import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import com.scheible.gwt.reduced.shared.framework.collection.Array;
import com.scheible.gwt.reduced.client.framework.fluent.ElementWrapper;

/**
 *
 * @author sj
 */
public class Grid<T> implements NativeGrid<T>, ElementWrapper {

    private final GridElement gridElement;
    private final Class<T> idClass;

    public Grid(String idAttribute, Class<T> idClass) {
        this(GridElement.create(idAttribute), idClass);
    }
    
    private Grid(GridElement<T> grid, Class<T> idClass) {
        this.gridElement = grid;
        this.idClass = idClass;
        
        if(!(idClass.equals(Long.class) 
                || idClass.equals(Integer.class) 
                || idClass.equals(String.class))) {
            throw new IllegalStateException("The id type of '" + idClass.getSimpleName() + "' is not supported!");
        }
    }    
    
    public static <T> Grid<T> findById(String id, Class<T> idClass) {
        return new Grid(GridElement.findById(id), idClass);
    }

    @Override
    public void addRowSelectionListener(RowSelectionListener<T> listener) {
        gridElement.addRowSelectionListener(id -> {
            if(id == null) {
                listener.onSelect(null);
            } else {
                if(idClass.equals(Long.class)) {
                    listener.onSelect((T) new Long(((Double) id).longValue()));
                } else if(idClass.equals(Integer.class)) {
                    listener.onSelect((T) new Integer(((Double) id).intValue()));
                } else { // idClass.getClass().equals(String.class)
                    listener.onSelect((T) id);
                }
            }
        });
    }

    @Override
    public void setRenderers(Array<CellRenderer> renderers) {
        gridElement.setRenderers(renderers);
    }

    @Override
    public void setRows(Array<String> titles, Array<Array<Object>> rows) {
        gridElement.setRows(titles, rows);
    }

    @Override
    public void setSelected(T id) {
        if (idClass.equals(Long.class)) {
            gridElement.setSelected(id != null ? ((Long)id).doubleValue() : null);
        } else if (idClass.equals(Integer.class)) {
            gridElement.setSelected(id != null ? ((Long)id).intValue() : null);
        } else { // idClass.getClass().equals(String.class)
            gridElement.setSelected(id);
        }        
    }
    
    @Override
    public T getSelected() {
        Object id = gridElement.getSelected();

        if(id == null) {
            return null;
        } else {
            if (idClass.equals(Long.class)) {
                return ((T) new Long(((Double) id).longValue()));
            } else if (idClass.equals(Integer.class)) {
                return ((T) new Integer(((Double) id).intValue()));
            } else { // idClass.getClass().equals(String.class)
                return ((T) id);
            }
        }
    }
    
    @Override
    public Element element() {
        return gridElement;
    }    
}
