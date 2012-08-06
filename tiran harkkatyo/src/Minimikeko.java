/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tanelvir
 */

import java.util.*;

public class Minimikeko {
    
    List<Solmu> h = new ArrayList<Solmu>();

  public Minimikeko() {
  }

  public Minimikeko(Solmu[] keys) {
    for (Solmu key : keys) {
      h.add(key);
    }
    for (int k = h.size() / 2 - 1; k >= 0; k--) {
      percolateDown(k, h.get(k));
    }
  }

  public void add(Solmu node) {
    h.add(null);
    int k = h.size() - 1;
    while (k > 0) {
      int parent = (k - 1) / 2;
      Solmu p = h.get(parent);
      if (node.compareTo(p) >= 0) {
        break;
      }
      h.set(k, p);
      k = parent;
    }
    h.set(k, node);
  }

  public Solmu remove() {
    Solmu removedNode = h.get(0);
    Solmu lastNode = h.remove(h.size() - 1);
    percolateDown(0, lastNode);
    return removedNode;
  }

  public Solmu min() {
    return h.get(0);
  }

  public boolean isEmpty() {
    return h.isEmpty();
  }

  void percolateDown(int k, Solmu node) {
    if (h.isEmpty()) {
      return;
    }
    while (k < h.size() / 2) {
      int child = 2 * k + 1;
      if (child < h.size() - 1 && h.get(child).compareTo(h.get(child + 1)) > 0) {
        child++;
      }
      if (node.compareTo(h.get(child)) <= 0) {
        break;
      }
      h.set(k, h.get(child));
      k = child;
    }
    h.set(k, node);
  }

  // Usage example
  public static void main(String[] args) {
    Solmu[] taulu = {new Solmu(1), new Solmu(2), new Solmu(3), new Solmu(4)};
    Minimikeko heap;
    heap = new Minimikeko(taulu);
    // print keys in sorted order
    while (!heap.isEmpty()) {
      System.out.println(heap.remove());
    }
  }
    
}
