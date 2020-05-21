package forms


object UserForm {

  import play.api.data._
  import play.api.data.Forms._

  case class UserData(name: String)

  val userForm: Form[UserData] = Form(
    mapping(
      "name" -> text
    )(UserData.apply)(UserData.unapply)
  )

}
