package com.scheible.gwt.reduced.client.framework.fluent;

import com.google.gwt.core.client.JavaScriptObject;
import com.scheible.gwt.reduced.client.framework.application.GwtApplication;
import com.scheible.gwt.reduced.client.framework.browser.Window;
import static com.scheible.gwt.reduced.client.framework.browser.Window.document;
import com.scheible.gwt.reduced.client.framework.browser.dom.Element;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Fluent API inspired by jQuery.
 * 
 * @author sj
 */
public class Fluent implements ElementWrapper {

    private static final String HIDDEN_CSS_CLASS;

    static {
        HIDDEN_CSS_CLASS = GwtApplication.getId() + "-hidden";
        
        $(Window.document().getHead()).append(
            $("<style>").expose(el -> el.setTextContent(
                "." + HIDDEN_CSS_CLASS + "{\n" +
                "   display: none !important;\n" + 
                "}")
            )
        );
    }

	private final Element element;

	private Fluent(Element element) {
		this.element = element;
	}

	public static Fluent $(Element element) {
		return new Fluent(element);
	}
    
    public static Fluent $(ElementWrapper wrapper) {
		return new Fluent(wrapper.element());
	}    

	public static Fluent $(String selectorOrElementName) {
		if (selectorOrElementName.startsWith("<") && selectorOrElementName.endsWith(">")) {
			return new Fluent(document().createElement(selectorOrElementName.substring(1, selectorOrElementName.length() - 1)));
		} else {
            return new Fluent(document().querySelector(selectorOrElementName));
        }
	}

	public static <T> List<Fluent> forEach(List<T> list, Function<T, Fluent> function) {
		return list.stream().map(function).collect(Collectors.toList());
	}
    
	public static <T> List<Fluent> forEachFlat(List<T> list, Function<T, List<Fluent>> function) {
		return list.stream().map(function).flatMap(fluents -> fluents.stream()).collect(Collectors.toList());
	}    
    
    public Fluent parent() {
        return new Fluent((Element) element.getParentNode());
    }
    
    public Optional<Fluent> findFirst(String selector) {
        Element queryResult = element.querySelector(selector);
        return Optional.ofNullable(queryResult != null ? new Fluent(queryResult) : null);
    }

	public Fluent attr(String attributeName, String value) {
		element.setAttribute(attributeName, value);
		return this;
	}
    
    public String attr(String attributeName) {
		return element.getAttribute(attributeName);
	}
    
    public Fluent id(String id) {
        return attr("id", id);
    }
    
    public String id() {
        return attr("id");
    }

	public Fluent css(String propertyName, String value) {
		element.getStyle().setProperty(propertyName, value);
		return this;
	}

	public Fluent append(Object... content) {
		for (Object object : content) {
			if (object instanceof ElementWrapper) {
                Element currentElement = ((ElementWrapper) object).element();
                // NOTE Java extended native JavaScript types like Grid must be double unwrapped because the Java 
                //      version (Grid) can't be used directly with browser APIs like appendChild (Fluent -> Grid -> Element).
                currentElement = currentElement instanceof ElementWrapper ? ((ElementWrapper) currentElement).element() : currentElement;
				element.appendChild(currentElement);
			} else if (object instanceof Collection) {
				append(((Collection) object).toArray());
			} else if (object instanceof Optional) {
				((Optional) object).ifPresent(presentObject -> element.appendChild(((Fluent) presentObject).element));
            } else if (object instanceof JavaScriptObject) { // NOTE Assume a native Element instance.
                element.appendChild(object);                    
			} else {
				throw new IllegalStateException("'" + object.getClass().getName() + "' type not supported for append(Object...)!");
			}
		}

		return this;
	}
    
    public Fluent addClass(Supplier<Boolean> condition, String className) {
        if(condition.get()) {
            return addClass(className);
        } 
        return this;
    }

	public Fluent addClass(String className) {
        if(!hasClass(className)) {
            element.getClassList().add(className);
        }
        return this;
	}
    
	public Fluent removeClass(String className) {
        if(hasClass(className)) {
            element.getClassList().remove(className);
        }
        return this;
	}
    
    public boolean hasClass(String className) {
        return element.getClassList().contains(className);
    }

	public Fluent text(String text) {
		element.setTextContent(text);
		return this;
	}

	public Fluent hide() {
		return addClass(HIDDEN_CSS_CLASS);
	}

	public Fluent show() {
		return removeClass(HIDDEN_CSS_CLASS);
	}
    
    public boolean isHidden() {
        return hasClass(HIDDEN_CSS_CLASS);
    }

    public Fluent toggle() {
        if(hasClass(HIDDEN_CSS_CLASS)) {
            return show();
        } else {
            return hide();
        }
	}
    
    public Fluent expose(Consumer<Element> consumer) {
        consumer.accept(element);
        return this;
    }

    @Override
	public Element element() {
		return element;
	}
}
