package com.bintangg.finalproject.navigation

enum class AppRoute(val route: String, val showBottomBar: Boolean = false) {
    DASHBOARD("Dashboard", showBottomBar = true),
    TASK_LIST("TaskList", showBottomBar = true),
    STUDY_PROGRESS("StudyProgress", showBottomBar = true),
    ADD_TASK("AddTask"),
    EDIT_TASK("EditTask"),
    CATEGORY_LIST("CategoryList"),
    ADD_CATEGORY("AddCategoryScreen"),
    EDIT_CATEGORY("EditCategory"),
    ADD_ROLE("AddRoleScreen"),
    EDIT_ROLE("EditRole"),
    ADD_SCHEDULE("AddSchedule"),
    EDIT_SCHEDULE("EditSchedule"),
    CREATE_ACCOUNT("CreateAccount"),
    LOGIN("Login")
}
