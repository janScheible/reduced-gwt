package com.scheible.gwt.reduced.shared;

import com.scheible.gwt.reduced.shared.framework.rest.RestResult;
import com.scheible.gwt.reduced.shared.framework.rest.RestService;

/**
 *
 * @author sj
 */
public interface TestService extends RestService {
    
    RestResult<TitleDto> getTitle();
    
    RestResult<GridDataDto> getGridData();
}
