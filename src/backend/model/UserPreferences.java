package backend.model;

public class UserPreferences {
    private int preferenceId;
    private int userId;
    private String theme;
    private String language;
    private int dailyStudyGoal; // in minutes
    private boolean emailNotifications;
    private boolean pushNotifications;
    private String defaultView; // dashboard, calendar, notes, courses
    private int pomodoroDuration; // in minutes
    private int breakDuration; // in minutes
    
    // Constants, values are for testing only, change when the system integration is going to start.
    public static final String THEME_LIGHT = "light"; 
    public static final String THEME_DARK = "dark";
    public static final String LANG_EN = "en";
    public static final String LANG_ES = "es";
    public static final String VIEW_DASHBOARD = "dashboard";
    public static final String VIEW_CALENDAR = "calendar";
    public static final String VIEW_NOTES = "notes";
    public static final String VIEW_COURSES = "courses";
    
    // Constructors
    public UserPreferences() {
        this.theme = THEME_LIGHT;
        this.language = LANG_EN;
        this.dailyStudyGoal = 120; // 2 hours
        this.emailNotifications = true;
        this.pushNotifications = true;
        this.defaultView = VIEW_DASHBOARD;
        this.pomodoroDuration = 25;
        this.breakDuration = 5;
    }
    
    public UserPreferences(int userId) {
        this();
        this.userId = userId;
    }
    
    // Getters and Setters
    public int getPreferenceId() { return preferenceId; }
    public void setPreferenceId(int preferenceId) { this.preferenceId = preferenceId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getTheme() { return theme; }
    public void setTheme(String theme) { 
        if (THEME_LIGHT.equals(theme) || THEME_DARK.equals(theme)) {
            this.theme = theme;
        }
    }
    
    public String getLanguage() { return language; }
    public void setLanguage(String language) { 
        if (LANG_EN.equals(language) || LANG_ES.equals(language)) {
            this.language = language;
        }
    }
    
    public int getDailyStudyGoal() { return dailyStudyGoal; }
    public void setDailyStudyGoal(int dailyStudyGoal) { 
        if (dailyStudyGoal >= 0 && dailyStudyGoal <= 480) { // 0-8 hours
            this.dailyStudyGoal = dailyStudyGoal;
        }
    }
    
    public boolean isEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(boolean emailNotifications) { 
        this.emailNotifications = emailNotifications; 
    }
    
    public boolean isPushNotifications() { return pushNotifications; }
    public void setPushNotifications(boolean pushNotifications) { 
        this.pushNotifications = pushNotifications; 
    }
    
    public String getDefaultView() { return defaultView; }
    public void setDefaultView(String defaultView) { 
        if (VIEW_DASHBOARD.equals(defaultView) || 
            VIEW_CALENDAR.equals(defaultView) || 
            VIEW_NOTES.equals(defaultView) || 
            VIEW_COURSES.equals(defaultView)) {
            this.defaultView = defaultView;
        }
    }
    
    public int getPomodoroDuration() { return pomodoroDuration; }
    public void setPomodoroDuration(int pomodoroDuration) { 
        if (pomodoroDuration >= 5 && pomodoroDuration <= 60) {
            this.pomodoroDuration = pomodoroDuration;
        }
    }
    
    public int getBreakDuration() { return breakDuration; }
    public void setBreakDuration(int breakDuration) { 
        if (breakDuration >= 1 && breakDuration <= 30) {
            this.breakDuration = breakDuration;
        }
    }
    
    // Helper methods
    public String getDailyStudyGoalFormatted() {
        int hours = dailyStudyGoal / 60;
        int minutes = dailyStudyGoal % 60;
        
        if (hours > 0 && minutes > 0) {
            return hours + "h " + minutes + "m";
        } else if (hours > 0) {
            return hours + " hour" + (hours > 1 ? "s" : "");
        } else {
            return minutes + " minute" + (minutes > 1 ? "s" : "");
        }
    }
    
    @Override
    public String toString() {
        return "UserPreferences{userId=" + userId + ", theme='" + theme + "', language='" + language + "'}";
    }
}
