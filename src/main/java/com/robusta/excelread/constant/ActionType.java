package com.robusta.excelread.constant;

public enum ActionType {
	
	SETCELLDATA("SETCELLDATA"),
	GETCELLDATA("GETCELLDATA");
	
	private final String text;

    /**
     * @param text
     */
	ActionType(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
