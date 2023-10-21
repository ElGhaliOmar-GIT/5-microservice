package tn.esprit.pidev.bns.entity.siwar;

public enum BusinessType {
    Physical_Person("Physical Person"),
    Business("Business"),
    Other("Other");

    private final String displayName;

    BusinessType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
