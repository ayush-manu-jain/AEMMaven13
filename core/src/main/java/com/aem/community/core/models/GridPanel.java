package com.aem.community.core.models;

import org.apache.commons.collections.CollectionUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Model class for Grid Panel component.
 * This class provides all required methods to support the Grid Panel component.
 *
 * @author ravik
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GridPanel {

    private static final String NN_PARSYS = "parsys";
    private static final int DEFAULT_PARSYS = 1;

    @Self
    private Resource resource;

    @ValueMapValue
    @Default(intValues = DEFAULT_PARSYS)
    private int lines;

    @ValueMapValue
    @Default(intValues = DEFAULT_PARSYS)
    private int columns;

    @ValueMapValue
    private boolean panelBackground;
    
    @ValueMapValue
    private String twopanelradioselect;
    
    @ValueMapValue
    private String threepanelradioselect;
    
    @ValueMapValue
    private String fourpanelradioselect;
    
    
    private List<List<String>> grid;

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    } 
    

	public boolean getPanelBackground() {
		return panelBackground;
	}

	public List<List<String>> getGrid() {
        if (CollectionUtils.isEmpty(grid)) {
            grid = new ArrayList<>();
            for (int lineCount = 1; lineCount <= lines; lineCount++) {
                final List<String> columnList = new ArrayList<>();
                for (int columnCount = 1; columnCount <= columns; columnCount++) {
                    columnList.add(NN_PARSYS + lineCount + columnCount);
                }
                grid.add(columnList);
            }
        }
        return grid;
    }
	
	public List<String> getPanelRadioSelect() {
		String panels[] = null;
		 final List<String> panelRadioSelect = new ArrayList<>();
		if(twopanelradioselect != null && columns==2) {
			panels = twopanelradioselect.split(",");
		} else if(threepanelradioselect != null && columns==3) {
			panels = threepanelradioselect.split(",");
		} else if(fourpanelradioselect != null && columns==4){
		    panels = fourpanelradioselect.split(",");
		}
		for(int i = 0; i < panels.length; i++)
		{
			panelRadioSelect.add(panels[i]);
		}
		return panelRadioSelect;
	}

}