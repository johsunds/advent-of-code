(ns advent-of-code.day-1)

(defn compute-fuel
  [mass]
  (-> (/ mass 3) (Math/floor) (- 2) (max 0)))

(defn compute-cumulative-fuel
  ([mass] (compute-cumulative-fuel mass 0))
  ([mass cumulative-fuel]
   (if (= mass 0)
     cumulative-fuel
     (let [fuel (compute-fuel mass)]
       (recur fuel (+ cumulative-fuel fuel))))))


(defn part-1
  []
  (->> (clojure.string/split-lines (slurp "inputs/1"))
       (map read-string)
       (map compute-fuel)
       (reduce +)))

(defn part-2
  []
  (->> (clojure.string/split-lines (slurp "inputs/1"))
       (map read-string)
       (map compute-cumulative-fuel)
       (reduce +)))