/**
 **************************************************************************
 * @author: Sanil Rijal
 *
 * Date: 4/20/2016
 * Program : This program implements breadth-first algorithm to find the paths
 * between the graphs. It reads input from a file and Processes the input.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PathFinder {
/** The provided graph class.*/
   Graph graph;
   /** A HashMap to count the number of edges to be travelled.*/
   HashMap<Integer, Integer> count;
   /** A HashMap to store the states and the interger value associated with it.*/
   HashMap<String, Integer> maps;
   int indexs = 0;
  
/* A constructor to read in the data from a text file which
 * contains a series of lines. Each line has the names of two graph vertices separated
 * by a single
space.
 *
 * @param filename- The file to be processed.
 */
   public PathFinder(String filename) {
      File file = new File(filename);
      String parts[];
      maps = new HashMap<String, Integer>();
      graph = new Graph();
      count = new HashMap<Integer, Integer>();
      int indexFirst, indexSecond;
      try {
         Scanner inFile = new Scanner(file);
         while (inFile.hasNextLine()) {
            parts = inFile.nextLine().split(" ");
         
            if (maps.get(parts[0]) == null)
               maps.put(parts[0], ++indexs);
            graph.addVertex(maps.get(parts[0]));
         
            if (maps.get(parts[1]) == null)
               maps.put(parts[1], ++indexs);
            graph.addVertex(maps.get(parts[1]));
         
            graph.addEdge(maps.get(parts[0]), maps.get(parts[1]));
         }
      	// System.out.println(graph);
      
      } 
      catch (FileNotFoundException e) {
      	// TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
/* A method to return the vertices in the
shortest path from source to destination,
 * using the format below. It should return “NONE” if there is
no path.
 * @return The vertices in the
shortest path from source to destination 
 */
   public String getPath(String source, String destination) {
   
      int v, w;
      if (maps.get(source) != null)
         v = maps.get(source);
      else
         throw new IllegalArgumentException("Invalid Vertex " + source);
   
      if (maps.get(destination) != null)
         w = maps.get(destination);
      else
         throw new IllegalArgumentException("Invalid Vertex " + destination);
   
      Queue<Integer> q = new LinkedList<Integer>();
      boolean[] visited = new boolean[graph.vertices()];
      String[] pathTo = new String[graph.vertices()];
   
      q.add(v);
      count = new HashMap<Integer, Integer>();
      pathTo[v] = source;
      count.put(v, 0);
      while (q.peek() != null) {
         if (runBFS(q.poll(), w, visited, q, pathTo, source, destination))
            break;
      }
      if (count.containsKey(w))
         return pathTo[w];
      else
         return "NONE";
   }
/* A method to return the smallest
number edges to be traveled in the path from source 
 * to destination. Throws an exception if any of
the vertices are invalid. Returns -1 if
 * no path exists.
 * @return The smallest
number edges to be traveled in the path from source to destination.
 */
 
   public int getPathLength(String source, String destination) {
   
      int v, w;
      if (maps.get(source) != null)
         v = maps.get(source);
      else
         throw new IllegalArgumentException("Invalid Vertex " + source);
   
      if (maps.get(destination) != null)
         w = maps.get(destination);
      else
         throw new IllegalArgumentException("Invalid Vertex " + destination);
   
      Queue<Integer> q = new LinkedList<Integer>();
      boolean[] visited = new boolean[graph.vertices()+1];
      String[] pathTo = new String[graph.vertices()+1];
   
      q.add(v);
      count = new HashMap<Integer, Integer>();
      pathTo[v] = source;
      count.put(v, 0);
      while (q.peek() != null) {
         if (runBFS(q.poll(), w, visited, q, pathTo, source, destination))
            break;
      }
      if (count.containsKey(w))
         return count.get(w);
      else
         return -1;
   }
/* A method to run breadth first search.*/ 
   private boolean runBFS(int v, int w, boolean[] visited, Queue<Integer> q, String[] pathTo, String source,
   		String destination) {
      if (visited[v]) {
      } 
      else if (v == w) {
         return true;
      } 
      else {
         visited[v] = true;
         Iterator<Integer> vi = graph.getAdjacent(v);
         while (vi.hasNext()) {
            int nextVertex = vi.next();
            if (count.containsKey(nextVertex)) {
               if (count.get(nextVertex) > (count.get(v) + 1)) {
                  pathTo[nextVertex] = pathTo[v] + " --> " + getKeyFromValue(maps, nextVertex);
                  count.put(nextVertex, (count.get(v) + 1));
               }
            } 
            else {
               pathTo[nextVertex] = pathTo[v] + " --> " + getKeyFromValue(maps, nextVertex);
               count.put(nextVertex, (count.get(v) + 1));
            }
            q.add(nextVertex);
         }
      }
      return false;
   }

   public static Object getKeyFromValue(Map hm, Object value) {
      for (Object o : hm.keySet()) {
         if (hm.get(o).equals(value)) {
            return o;
         }
      }
      return null;
   }
/*A method to return a set of strings
containing vertices reachable by traveling no more
 * than ‘distance’ edges from the source.
 * @return A set of strings
containing vertices reachable by traveling no more than 
 * ‘distance’ edges from the source.
 */
   public Set<String> reachable(String source, int distance) {
      int v;
   
      if (maps.get(source) != null)
         v = maps.get(source);
      else
         throw new IllegalArgumentException("Invalid Vertex " + source);
   
      Set<String> returnValue = new HashSet<String>();
   
      Queue<Integer> q = new LinkedList<Integer>();
      boolean[] visited = new boolean[graph.vertices()];
      String[] pathTo = new String[graph.vertices()];
      q.add(v);
      count.clear();
      count.put(v, 0);
      returnValue.add(source);
   	//System.out.println(source);
      while (q.peek() != null) {
         v = q.poll();
         if (visited[v]) {
         } 
         else {
            visited[v] = true;
            Iterator<Integer> vi = graph.getAdjacent(v);
            while (vi.hasNext()) {
               int nextVertex = vi.next();
               if (!count.containsKey(nextVertex)) {
                  count.put(nextVertex, (count.get(v) + 1));
                  if (count.get(nextVertex) <= distance) {
                  //	System.out.println(getKeyFromValue(maps, nextVertex));
                     returnValue.add((String) getKeyFromValue(maps, nextVertex));
                  }
               }
               q.add(nextVertex);
            }
         }
      }
   
      return returnValue;
   
   }
}
