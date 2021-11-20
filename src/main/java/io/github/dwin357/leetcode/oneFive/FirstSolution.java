package io.github.dwin357.leetcode.oneFive;

import java.util.*;

public class FirstSolution {
    /*
     * Problem: Given an integer array nums,
     * return all the triplets [nums[i], nums[j], nums[k]]
     * such that i != j, i != k, and j != k,
     * and nums[i] + nums[j] + nums[k] == 0
     * ...and such that every triplet in the result-set is unique (which I am taking to mean uniq-combination)
     *
     * (my laptop died and I lost all my notes -salty- at least no actual work had happened yet)
     *
     * The solution that I came to is a graph, even though I am sure it is not the "correct" one for this problem
     *
     * Have an artificial root node that you drop el into one by one; the fact that each is a uniq node address i != j != k stuff
     * Assuming the root node is 0 and the edge of each node is the value at that position,
     * a successful path through the graph is anyone that is exactly 3 steps long and ends at the origin/zero
     *
     * However, the graph is "non-deterministic" (the best term I could come up with) --by which I mean,
     * for any given node the graph must represent both the possibility that it did & did not happen.
     * Practically speaking what this means is, any time a new el is to be added to the graph, the entire graph
     * needs to be deep-cloned, the new element is added to one side, and the 2 sides combined into the new graph.
     * (and again, I'm sure this is not the "correct" solution --but I'm also pretty sure it will work)
     *
     * So that is the hard part, now the easy part.  B/c the base case can be identified on an incomplete graph,
     * we can preemptively prune paths on the graph which are known to lead to bad outcomes (ie anything 3 steps
     * long != 0, or anything more than 3 steps long) which in trun should significantly cut down on the amount of
     * work needed to deep-clone the graph for adding new elements.
     *
     * Additionally, a side effect of the graph as a whole being non-deterministic, is that every path w/in the graph
     * (including intermediary/sub-paths) is both uniq & preserved.  As a result of this, the conceptual graph can be
     * represented in the program as a set of path-aggregations.
     * 
     * wha wha whaa - time limit exceeded
     */

    public List<List<Integer>> threeSum(int[] nums) {
        Graph g = new Graph();
        for(int num : nums) {
            g.append(num);
        }
        return g.results();
    }

    private class Graph {
        private Set<PathAggregation> graph;

        public Graph() {
            this.graph = new HashSet<>();
        }

        public void append(int edge) {
            Set<PathAggregation> next = new HashSet<>(graph);
            next.add(new PathAggregation(edge));
            for(PathAggregation path : graph) {
                PathAggregation step = path.step(edge);
                if(step.isOpen() || step.isValid()) {
                    next.add(step);
                }
            }
            this.graph = next;
        }

        public List<List<Integer>> results() {
            List<List<Integer>> results = new ArrayList<>();
            for(PathAggregation path : graph) {
                if(path.isValid()) {
                    List<Integer> pv = new ArrayList<>();
                    for(int i=0; i<path.values.length; i++) {
                        pv.add(path.values[i]);
                    }
                    results.add(pv);
                }
            }
            return results;
        }
    }

    private class PathAggregation {
        public final int steps;
        public final int position;
        public final int[] values;


        public PathAggregation(int edge) {
            this(1,edge, new int[] {edge});
        }

        private PathAggregation(int steps, int position, int[] values) {
            this.steps = steps;
            this.position = position;
            this.values = values;
        }

        public PathAggregation step(int edge) {
            int[] v = Arrays.copyOf(values, steps+1);
            v[steps] = edge;
            Arrays.sort(v);

            return new PathAggregation(steps+1, position+edge, v);
        }

        public boolean isOpen() {
            return steps < 3;
        }

        public boolean isValid() {
            return steps == 3 && position == 0;
        }

        /////////////////////////////////
        ////  Auto-gen boiler blate  ////
        /////////////////////////////////
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PathAggregation that = (PathAggregation) o;

            if (steps != that.steps) return false;
            if (position != that.position) return false;
            return Arrays.equals(values, that.values);
        }

        @Override
        public int hashCode() {
            int result = steps;
            result = 31 * result + position;
            result = 31 * result + Arrays.hashCode(values);
            return result;
        }
    }
}


