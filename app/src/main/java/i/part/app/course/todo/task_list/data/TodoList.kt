package i.part.app.course.todo.task_list.data

data class TodoList(val viewType: Int, var todoListName: String, var isCompleted: Boolean){
    companion object{
        val ADD_TODOLIST_BUTTON = 0
        val TODOLIST = 1
    }
}
