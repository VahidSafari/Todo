package i.part.app.course.todo.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import i.part.app.course.todo.features.board.data.*

@Database(entities = [BoardEntity::class,TaskEntity::class, TodoEntity::class, BoardMemberEntity::class, MemberOfBoardEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

//    abstract fun getUserDao():UserDao
abstract fun getBoardDao(): BoardDao

    companion object {
        private var db: TodoDatabase? = null
        private const val databaseName = "todo.db"

        fun init(context: Context) {
            if (db == null) {
                db = Room.databaseBuilder(context, TodoDatabase::class.java, databaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance(): TodoDatabase? {
            return db
        }
    }
}