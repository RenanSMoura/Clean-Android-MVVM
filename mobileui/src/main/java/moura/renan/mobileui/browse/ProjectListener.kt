package moura.renan.mobileui.browse

interface ProjectListener {
    fun onBookmarkedProjectClicked(projectId: String)
    fun onProjectClicked(projectId: String)
}