(ns htt-clacks.core)


;; Create an alphabet to convert strings to binary codes

(def alphabet {"a"  [0 1 1 0 0 0 0 1 ] "b"  [0 1 1 0 0 0 1 0 ] "c"  [0 1 1 0 0 0 1 1 ] "d"  [0 1 1 0 0 1 0 0 ]
               "e"  [0 1 1 0 0 1 0 1 ] "f"  [0 1 1 0 0 1 1 0 ] "g"  [0 1 1 0 0 1 1 1 ] "h"  [0 1 1 0 1 0 0 0 ]
               "i"  [0 1 1 0 1 0 0 1 ] "j"  [0 1 1 0 1 0 1 0 ] "k"  [0 1 1 0 1 0 1 1 ] "l"  [0 1 1 0 1 1 0 0 ]
               "m"  [0 1 1 0 1 1 0 1 ] "n"  [0 1 1 0 1 1 1 0 ] "o"  [0 1 1 0 1 1 1 1 ] "p"  [0 1 1 1 0 0 0 0 ]
               "q"  [0 1 1 1 0 0 0 1 ] "r"  [0 1 1 1 0 0 1 0 ] "s"  [0 1 1 1 0 0 1 1 ] "t"  [0 1 1 1 0 1 0 0 ]
               "u"  [0 1 1 1 0 1 0 1 ] "v"  [0 1 1 1 0 1 1 0 ] "w"  [0 1 1 1 0 1 1 1 ] "x"  [0 1 1 1 1 0 0 0 ]
               "y"  [0 1 1 1 1 0 0 1 ] "z"  [0 1 1 1 1 0 1 0 ] "A"  [0 1 0 0 0 0 0 1 ] "B"  [0 1 0 0 0 0 1 0 ]
               "C"  [0 1 0 0 0 0 1 1 ] "D"  [0 1 0 0 0 1 0 0 ] "E"  [0 1 0 0 0 1 0 1 ] "F"  [0 1 0 0 0 1 1 0 ]
               "G"  [0 1 0 0 0 1 1 1 ] "H"  [0 1 0 0 1 0 0 0 ] "I"  [0 1 0 0 1 0 0 1 ] "J"  [0 1 0 0 1 0 1 0 ]
               "K"  [0 1 0 0 1 0 1 1 ] "L"  [0 1 0 0 1 1 0 0 ] "M"  [0 1 0 0 1 1 0 1 ] "N"  [0 1 0 0 1 1 1 0 ]
               "O"  [0 1 0 0 1 1 1 1 ] "P"  [0 1 0 1 0 0 0 0 ] "Q"  [0 1 0 1 0 0 0 1 ] "R"  [0 1 0 1 0 0 1 0 ]
               "S"  [0 1 0 1 0 0 1 1 ] "T"  [0 1 0 1 0 1 0 0 ] "U"  [0 1 0 1 0 1 0 1 ] "V"  [0 1 0 1 0 1 1 0 ]
               "W"  [0 1 0 1 0 1 1 1 ] "X"  [0 1 0 1 1 0 0 0 ] "Y"  [0 1 0 1 1 0 0 1 ] "Z"  [0 1 0 1 1 0 1 0 ]
               " "  [0 0 0 0 0 0 0 0 ]})

(alphabet "A")
;; => [0 1 0 0 0 0 0 1]


;; Convert a character to binary code

(defn character->clack [character alphabet]
  (alphabet character))

(character->clack "c" alphabet)
;; => [0 1 1 0 0 0 1 1]


;; Convert a string to a binary code

(defn word->clack [word alphabet]
  (map #(character->clack % alphabet) (map str word)))

(word->clack "hello" alphabet)
;; => ([0 1 1 0 1 0 0 0] [0 1 1 0 0 1 0 1] [0 1 1 0 1 1 0 0] [0 1 1 0 1 1 0 0] [0 1 1 0 1 1 1 1])

;; Create inverted alphabet to convert binary codes to string

(def alphabet-inverted (clojure.set/map-invert alphabet))

(alphabet-inverted [0 1 1 0 0 0 1 1])
;; => "c"

(defn clack->character [clack alphabet]
  (get alphabet-inverted clack))

(clack->character [0 1 1 0 0 0 0 1] alphabet)
;; => "a"

(defn clack->message [clack alphabet-inverted]
  (apply str (map #(clack->character % alphabet-inverted) clack )))

;; test data
(word->clack "hello world" alphabet)
;; => ([0 1 1 0 1 0 0 0] [0 1 1 0 0 1 0 1] [0 1 1 0 1 1 0 0] [0 1 1 0 1 1 0 0] [0 1 1 0 1 1 1 1] [0 0 0 0 0 0 0 0] [0 1 1 1 0 1 1 1] [0 1 1 0 1 1 1 1] [0 1 1 1 0 0 1 0] [0 1 1 0 1 1 0 0] [0 1 1 0 0 1 0 0])

(clack->message '([0 1 1 0 1 0 0 0] [0 1 1 0 0 1 0 1] [0 1 1 0 1 1 0 0] [0 1 1 0 1 1 0 0]
                  [0 1 1 0 1 1 1 1] [0 0 0 0 0 0 0 0] [0 1 1 1 0 1 1 1] [0 1 1 0 1 1 1 1]
                  [0 1 1 1 0 0 1 0] [0 1 1 0 1 1 0 0] [0 1 1 0 0 1 0 0]) alphabet-inverted)
;; => "hello world"
