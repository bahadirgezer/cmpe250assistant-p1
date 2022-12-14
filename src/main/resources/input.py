
DESTINATION_DIRECTORY = "inputs"
INPUT_NAME = "case"
INPUT_EXTENSION = "in"

'''
AF: addFirst            - "AF <product_id> <product_value>"
AL: addLast             - "AL <product_id> <product_value>"
RF: removeFirst         - "RF"
RL: removeLast          - "RL"
F:  find                - "F <product_id>"
U:  update              - "U <product_id> <updated_value>"
G:  get                 - "G <index>"
P:  print               - "P"
RI: removeIndex         - "RI <index>"
RP: removeProduct       - "RP <product_value>"
FD: filterDuplicates    - "FD"
R:  reverse             - "R"
A:  add                 - "A <index> <product_id> <product_value>"
'''
class Input:
    def __init__(self, input_length):
        self.input_length: int = input_length
        self.generate()

    def generate(self) -> None:
        return

    def __repr__(self):
        return ""

if __name__ == "__main__":
    inn = 1
    too = 3
    for i in range(inn, inn + too):
        print("generating input ", f"{DESTINATION_DIRECTORY}/{INPUT_NAME}{i}.{INPUT_EXTENSION}")
        file = open(f"{DESTINATION_DIRECTORY}/{INPUT_NAME}{i}.{INPUT_EXTENSION}", "w")
        inn = Input(10)
        file.write(str(inn))
        file.close()

    exit(0)
