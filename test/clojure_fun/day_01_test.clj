(ns clojure-fun.day-01-test
  (:require [clojure.test :as t]
            [clojure-fun.day-01 :as day-01]))

(t/deftest day-01-test
  (t/testing "Part 1"
    (t/is (= 12 (day-01/part-1 "R5, L5, R5, R3")))
    (t/is (= 300 (day-01/part-1 day-01/input)))))
