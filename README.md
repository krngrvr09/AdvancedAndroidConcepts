# AdvancedAndroidConcepts
Codes for all topics that were to be covered in the Byld talk.

* **InitialProblemActivity** - Demonstrates the problem of doing long operations without using Threads.
* **BasicPipedActivity** - Demonstrates a solution of the above problem using Pipes.

Problem with Pipes - Buffer is finite, will fill up often and will block the Thread.

* **NoLooperActivity** - Demostrates the same problem of not using Threads in a different way.
* **LooperActivity** - Solves the above problem using Handlers and Loopers.
* **RaceConditionActivity** - Demostrates the problem of race condition with using Handlers and Loopers. Handler of the other thread may not be initialized when you try to access it.
* **HandlerThreadActivity** - Demonstrates the solution of the above problem by using HandlerThreads which have loopers already initialized, and the looper is passed to the Handler separately.
* **ImageActivity** - Demonstrate the problem of displaying big images in the Activity. Will give OutOfMemoryException. Demonstrated the Solution in the main Activity, by scaling down the image.
* **GridViewActivity** - Displaying high definition images in a grid, using Adapters. Gives OutOfMemoryException.
* **GridViewGoodActivity** - Demonstrates the solution, by using AsyncTask to process images in a different Thread. And using WeakReferences to take into account the fact that views get receycled.
