package moura.renan.domain.model

//Esse módulo representa a nossa lógica de negócio da aplicação
class Project (val id : String, val name : String,
               val fullName : String, val startCount : String,
               val dateCreated : String, val ownerName : String,
               val ownerAvatar : String, val isBookmarked : Boolean)