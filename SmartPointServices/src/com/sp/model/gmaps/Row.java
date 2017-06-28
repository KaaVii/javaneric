
package com.sp.model.gmaps;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {
	/**
	 * @author dan
	 */
    @SerializedName("elements")
    @Expose
    private List<Element> elements = null;

    /**
     * 
     * @return
     *     The elements
     */
    public List<Element> getElements() {
        return elements;
    }

    /**
     * 
     * @param elements
     *     The elements
     */
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}
