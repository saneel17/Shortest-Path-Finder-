/**
   Program to perform basic testing of the PathFinder class.
*/
public class PathFinderTest
{     
   /**
      Calls to methods to display length and vertices in path from source to destination.
      @param paths the PathFinder object containing graph data
      @param source the source vertex
      @param dest the destination vertex
   */
   public static void showPath(PathFinder paths, String source, String dest)
   {
      System.out.printf("When traveling from %s to %s you will cross %d borders:\n", 
                        source, dest, paths.getPathLength(source, dest));
     System.out.println(paths.getPath(source, dest));
   }
   
   public static void main (String[] args)
   {
      PathFinder paths = new PathFinder("contiguous-usa.dat");

     showPath(paths, "LA", "TX");
       showPath(paths, "LA", "FL");
     // showPath(paths, "AL", "OR");
      System.out.printf("From %s you can reach %s while crossing at most %d borders\n", 
                         "LA", paths.reachable("LA", 2), 2);      
       System.out.printf("From %s you can reach %s while crossing at most %d borders\n", 
                         "KS", paths.reachable("KS", 2), 2);

    }
}
