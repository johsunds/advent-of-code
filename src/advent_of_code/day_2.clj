(ns advent-of-code.day-2
  (:require [clojure.test :refer :all]))

(defn do-op
  [f program args]
  (->> (apply f (map program (butlast args)))
       (assoc program (last args))))

(defn consume-expr
  [program ip]
  (let [[op & args] (subvec program ip (min (count program) (+ ip 4)))]
    (case op
      1 [(do-op + program args) (+ ip 4)]
      2 [(do-op * program args) (+ ip 4)]
      99 [program (count program)])))

(defn run-program
  ([program] (run-program program 0))
  ([program ip]
   (let [[new-program new-ip] (consume-expr program ip)]
     (if (nil? (get new-program new-ip))
       new-program
       (recur new-program new-ip)))))

(deftest test-programs
  (testing "a couple of small programs")
  (is (= (run-program [1 1 1 4 99 5 6 0 99])
         [30 1 1 4 2 5 6 0 99]))
  (is (= (run-program [2 4 4 5 99 0])
         [2 4 4 5 99 9801]))
  (is (= (run-program [2 3 0 3 99])
         [2 3 0 6 99])))

(defn part-1
  []
  (-> (map read-string (clojure.string/split (slurp "inputs/2") #","))
      (vec)
      (assoc 1 12)
      (assoc 2 2)
      (run-program)))