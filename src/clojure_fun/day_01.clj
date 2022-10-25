(ns clojure-fun.day-01
  (:require [clojure.string :as string]))

(def input (slurp "resources/day_01/input.txt"))

(defn parse-instruction [input]
  (->> input
       (#(string/split % #""))
       ((fn [seq]
          {:direction (first seq)
           :distance (->> seq rest (string/join "") read-string)}))))

(defn parse-input [input]
  (->> input
       (#(string/split % #",\s?"))
       (map parse-instruction)))

(def compass [:N :E :S :W])

(defn turn-right [orientation]
  (-> (get compass (+ (.indexOf compass orientation) 1))
      (or :N)))

(defn turn-left [orientation]
  (->  (get compass (- (.indexOf compass orientation) 1))
       (or :W)))

(defn run-instructions [instructions]
  (reduce
   (fn [acc instruction]
     (let
      [orientation (:orientation acc)
       nextOrientation ((if (= "L" (:direction instruction)) turn-left turn-right) orientation)
       distance (:distance instruction)]
       (-> (case nextOrientation
             :N (-> acc  (update :dy #(+ % distance)))
             :S (-> acc  (update :dy #(- % distance)))
             :E (-> acc  (update :dx #(+ % distance)))
             :W (-> acc  (update :dx #(- % distance))))
           (assoc :orientation nextOrientation))))
   {:dx 0 :dy 0 :orientation :N}
   instructions))

(defn part-1 [input]
  (->> (parse-input input)
       run-instructions
       (#(+ (abs (:dx %)) (abs (:dy %))))))

