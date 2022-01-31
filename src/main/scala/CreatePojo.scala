

object CreatePojo {
  def main(args: Array[String]): Unit = {
    val noneValue = Option.empty[Long]
    val oneValue = Some(1L)

    def toJavaLong(l: Option[Long]): java.lang.Long =
      if (l.isEmpty) l.get else null

    // Next line works fine.
    new ExamplePojo(toJavaLong(noneValue))

    // Next line works fine.
    new ExamplePojo(oneValue.get)

    // Next line throws a compilation error:
    // type arguments [Long] do not conform to method orNull's type parameter bounds [A1 >: Long]
    new ExamplePojo(noneValue.orNull)

    // Next line throws a compilation error:
    // type mismatch;
    //  found   : Any
    //  required: Long
    new ExamplePojo(noneValue.getOrElse(null))

    // This will throw a `java.util.NoSuchElementException`
    new ExamplePojo(noneValue.get)
  }
}
