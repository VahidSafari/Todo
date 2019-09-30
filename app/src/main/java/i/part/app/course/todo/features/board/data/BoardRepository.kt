package i.part.app.course.todo.features.board.data


import androidx.lifecycle.MutableLiveData
import i.part.app.course.todo.core.api.Result
import i.part.app.course.todo.core.api.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BoardRepository {
    private val retrofit = RetrofitFactory.getRetrofit()
    private val boardServices = retrofit?.create(BoardServices::class.java)


    fun addTodoList(
        todoListName: String,
        boardId: Int
    ): MutableLiveData<Result<AddTodoListResponse?>> {
        val result = MutableLiveData<Result<AddTodoListResponse?>>()
        //result.value = Result.Loading<>
        val call: Call<AddTodoListResponse>? = boardServices?.createTodoList(
            boardID = boardId,
            title = AddTodoListParam(todoListName)
        )
        call?.enqueue(object : Callback<AddTodoListResponse> {
            override fun onFailure(call: Call<AddTodoListResponse>, t: Throwable) {
                result.value = Result.Error("ConnectionError", null)
            }

            override fun onResponse(
                call: Call<AddTodoListResponse>,
                response: Response<AddTodoListResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body())
//                    (result.value as Result.Success).data?.todoListName = todoListName
                }
            }

        })
        return result
    }

    fun loadTodoLists(boardID: Int): MutableLiveData<Result<ThisBoardTodoListResponse?>> {
        var result = MutableLiveData<Result<ThisBoardTodoListResponse?>>()
//        result.value = Result.Loading()
        val call: Call<ThisBoardTodoListResponse>? = boardServices?.getAllTodoList(boardID)
        call?.enqueue(object : Callback<ThisBoardTodoListResponse> {
            override fun onFailure(call: Call<ThisBoardTodoListResponse>, t: Throwable) {
                result.value = Result.Error("ConnectionError", null)
            }

            override fun onResponse(
                call: Call<ThisBoardTodoListResponse>,
                response: Response<ThisBoardTodoListResponse?>
            ) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body())
                }
            }

        })
        return result
    }


    fun getTasks(todoListId: Int): MutableLiveData<Result<List<TaskResponse>?>> {
        var result = MutableLiveData<Result<List<TaskResponse>?>>()
        //result.value = Result.Loading<>
        val call: Call<List<TaskResponse>>? =
            boardServices?.getTasks(id = todoListId)//TODO board id shoud be give
        call?.enqueue(object : Callback<List<TaskResponse>> {
            override fun onFailure(call: Call<List<TaskResponse>>, t: Throwable) {
                result.value = Result.Error("ConnectionError", null)
            }

            override fun onResponse(
                call: Call<List<TaskResponse>>,
                response: Response<List<TaskResponse>>
            ) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body())
                }
            }

        })
        return result
    }

    fun editTodoList(
        todoListName: String,
        id: Int
    ): MutableLiveData<Result<EditTodoListResponse?>> {
        val result = MutableLiveData<Result<EditTodoListResponse?>>()
        //result.value = Result.Loading<>
        val call: Call<EditTodoListResponse>? = boardServices?.editTodoListName(
            id = id,
            title = AddTodoListParam(todoListName)
        )
        call?.enqueue(object : Callback<EditTodoListResponse> {
            override fun onFailure(call: Call<EditTodoListResponse>, t: Throwable) {
                result.value = Result.Error("ConnectionError", null)
            }

            override fun onResponse(
                call: Call<EditTodoListResponse>,
                response: Response<EditTodoListResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body())
                } else if (response.code() == 500) {
                    result.value = Result.Error("ServerError")
                }
            }

        })
        return result
    }

    fun addTask(
        addTaskParam: AddTaskParam,
        id: Int
    ): MutableLiveData<Result<AddTaskResponse?>> {
        val result = MutableLiveData<Result<AddTaskResponse?>>()
        //result.value = Result.Loading<>
        val call: Call<AddTaskResponse>? =
            boardServices?.addTask(id = id, addTask = addTaskParam)
        call?.enqueue(object : Callback<AddTaskResponse> {
            override fun onFailure(call: Call<AddTaskResponse>, t: Throwable) {
                result.value = Result.Error("ConnectionError", null)
            }

            override fun onResponse(
                call: Call<AddTaskResponse>,
                response: Response<AddTaskResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body())
                }
            }

        })
        return result
    }

    fun editTask(
        addTaskParam: AddTaskParam,
        id: Int
    ): MutableLiveData<Result<EditTaskResponse?>> {
        val result = MutableLiveData<Result<EditTaskResponse?>>()
        //result.value = Result.Loading<>
        val call: Call<EditTaskResponse>? =
            boardServices?.editTask(id = id, addTask = addTaskParam)
        call?.enqueue(object : Callback<EditTaskResponse> {
            override fun onFailure(call: Call<EditTaskResponse>, t: Throwable) {
                result.value = Result.Error("ConnectionError", null)
            }

            override fun onResponse(
                call: Call<EditTaskResponse>,
                response: Response<EditTaskResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body())
                } else if (response.code() == 500) {
                    result.value = Result.Error("ServerError")
                }
            }

        })
        return result
    }

    fun getBoards(): MutableLiveData<Result<List<BoardResponse>?>> {
        val result = MutableLiveData<Result<List<BoardResponse>?>>()
        val call: Call<List<BoardResponse>>? = boardServices?.getBoards()
        call?.enqueue(object : Callback<List<BoardResponse>> {
            override fun onFailure(call: Call<List<BoardResponse>>, t: Throwable) {
                result.value = Result.Error("Connection error")
            }

            override fun onResponse(
                call: Call<List<BoardResponse>>,
                response: Response<List<BoardResponse>>
            ) {
                when {
                    response.isSuccessful -> result.value = Result.Success(response.body())
                    response.code() == 401 -> result.value = Result.Error("Unauthorized")
                }
            }
        })
        return result
    }

    fun createBoard(newBoardParam: NewBoardParam): MutableLiveData<Result<NewBoardResponse?>> {
        val result = MutableLiveData<Result<NewBoardResponse?>>()
        val call: Call<NewBoardResponse>? =
            boardServices?.createBoard(newBoardParam = newBoardParam)
        call?.enqueue(object : Callback<NewBoardResponse> {
            override fun onFailure(call: Call<NewBoardResponse>, t: Throwable) {
                result.value = Result.Error("Connection error", null)
            }

            override fun onResponse(
                call: Call<NewBoardResponse>,
                response: Response<NewBoardResponse>
            ) {
                when {
                    response.isSuccessful -> result.value = Result.Success(response.body())
                    response.code() == 400 -> result.value =
                        Result.Error(response.body()?.message ?: "invalid request")
                    response.code() == 401 -> result.value =
                        Result.Error(response.body()?.message ?: "Unauthorized")
                }
            }
        })
        return result
    }

    fun getBoardById(id: Int): MutableLiveData<Result<BoardDetailResponse?>> {
        val result = MutableLiveData<Result<BoardDetailResponse?>>()
        val call: Call<BoardDetailResponse>? = boardServices?.getBoardById(id = id)
        call?.enqueue(object : Callback<BoardDetailResponse> {
            override fun onFailure(call: Call<BoardDetailResponse>, t: Throwable) {
                result.value = Result.Error("Connection error", null)
            }

            override fun onResponse(
                call: Call<BoardDetailResponse>,
                response: Response<BoardDetailResponse>
            ) {
                when {
                    response.isSuccessful -> result.value = Result.Success(response.body())
                    response.code() == 404 -> result.value =
                        Result.Error(response.message() ?: "Board not found")
                    response.code() == 401 -> result.value =
                        Result.Error(response.message() ?: "Unauthorized")
                }
            }
        })
        return result
    }

    fun updateBoardTitle(id: Int, title: String): MutableLiveData<Result<UpdateBoardResponse?>> {
        val result = MutableLiveData<Result<UpdateBoardResponse?>>()
        val call: Call<UpdateBoardResponse>? =
            boardServices?.updateBoardTitle(id = id, updateBoardParam = UpdateBoardParam(title))
        call?.enqueue(object : Callback<UpdateBoardResponse> {
            override fun onFailure(call: Call<UpdateBoardResponse>, t: Throwable) {
                result.value = Result.Error("ConnectionError", null)
            }

            override fun onResponse(
                call: Call<UpdateBoardResponse>,
                response: Response<UpdateBoardResponse>
            ) {
                when {
                    response.isSuccessful -> result.value = Result.Success(response.body())
                    response.code() == 400 -> result.value =
                        Result.Error(response.message() ?: "Body is not valid")
                    response.code() == 404 -> result.value =
                        Result.Error(response.message() ?: "Board not found")
                    response.code() == 500 -> result.value =
                        Result.Error(response.message() ?: "Internal server error")
                }
            }
        })
        return result
    }

    fun deleteBoardById(id: Int): MutableLiveData<Result<DeleteBoardResponse?>> {
        val result = MutableLiveData<Result<DeleteBoardResponse?>>()
        val call: Call<DeleteBoardResponse>? = boardServices?.deleteBoardById(id = id)
        call?.enqueue(object : Callback<DeleteBoardResponse> {
            override fun onFailure(call: Call<DeleteBoardResponse>, t: Throwable) {
                result.value = Result.Error("ConnectionError", null)
            }

            override fun onResponse(
                call: Call<DeleteBoardResponse>,
                response: Response<DeleteBoardResponse>
            ) {
                when {
                    response.isSuccessful -> result.value = Result.Success(response.body())
                    response.code() == 404 -> result.value =
                        Result.Error(response.message() ?: "Board not found")
                }
            }
        })
        return result
    }

    fun getCurrentTodos(): MutableLiveData<Result<List<TodoSpecification>?>> {
        val result = MutableLiveData<Result<List<TodoSpecification>?>>()
        val call: Call<List<TodoSpecification>>? = boardServices?.getCurrentTodos()
        call?.enqueue(object : Callback<List<TodoSpecification>> {
            override fun onFailure(call: Call<List<TodoSpecification>>, t: Throwable) {
                result.value = Result.Error("ConnectionError", null)
            }

            override fun onResponse(
                call: Call<List<TodoSpecification>>,
                response: Response<List<TodoSpecification>>
            ) {
                when {
                    response.isSuccessful -> result.value = Result.Success(response.body())
                    else -> result.value = Result.Error("Invalid request")
                }
            }
        })
        return result
    }

}