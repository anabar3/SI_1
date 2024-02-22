package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.SearchProblem;

public class VacuumCleanerProblem extends SearchProblem {
    public static class VacuumCleanerState extends State {
        public enum RobotPosition {LEFT, RIGHT};
        public enum DirtPosition {BOTH, RIGHT, LEFT, NONE};

        private RobotPosition robotPosition;
        private DirtPosition dirtPosition;

        public VacuumCleanerState(RobotPosition robotPosition, DirtPosition dirtPosition) {
            this.robotPosition = robotPosition;
            this.dirtPosition = dirtPosition;
        }

        @Override
        public String toString() {
            return "(" + robotPosition + "," + dirtPosition + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            VacuumCleanerState that = (VacuumCleanerState) o;

            if (robotPosition != that.robotPosition) return false;
            return dirtPosition == that.dirtPosition;
        }

        @Override
        public int hashCode() {
            int result = robotPosition.hashCode();
            result = 31 * result + dirtPosition.hashCode();
            return result;
        }
    }

    public static class VacuumCleanerAction extends Action {
        public enum Type {LEFT, RIGHT, CLEAN};

        private Type type;

        public VacuumCleanerAction(Type type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type.name();
        }

        @Override
        public boolean isApplicable(State st) {
            return true;
        }

        @Override
        public State applyTo(State st) {
            VacuumCleanerState vcSt = (VacuumCleanerState) st;
            VacuumCleanerState.RobotPosition newRobotPosition = vcSt.robotPosition;
            VacuumCleanerState.DirtPosition newDirtPosition = vcSt.dirtPosition;

            if (type == Type.LEFT)
                newRobotPosition = VacuumCleanerState.RobotPosition.LEFT;
            else if (type == Type.RIGHT)
                newRobotPosition = VacuumCleanerState.RobotPosition.RIGHT;
            else if (type == Type.CLEAN) {
                if (vcSt.robotPosition == VacuumCleanerState.RobotPosition.LEFT) { //Vacuum left
                    if ((vcSt.dirtPosition == VacuumCleanerState.DirtPosition.RIGHT) ||
                            (vcSt.dirtPosition == VacuumCleanerState.DirtPosition.BOTH)) {
                        newDirtPosition = VacuumCleanerState.DirtPosition.RIGHT;
                    }
                    else
                        newDirtPosition = VacuumCleanerState.DirtPosition.NONE;
                }
                else{ //Vacuum right
                    if ((vcSt.dirtPosition == VacuumCleanerState.DirtPosition.LEFT) ||
                            (vcSt.dirtPosition == VacuumCleanerState.DirtPosition.BOTH)) {
                        newDirtPosition = VacuumCleanerState.DirtPosition.LEFT;
                    }
                    else
                        newDirtPosition = VacuumCleanerState.DirtPosition.NONE;
                }
            }
            return new VacuumCleanerState(newRobotPosition, newDirtPosition);
        }
    }

    //Since all actions can be applied to any state and there are few of them,
    //we can keep them in a precomputed array to be returned by the "actions" method.
    private Action[] actionList;

    public VacuumCleanerProblem(VacuumCleanerState initialState) {
        super(initialState);
        //Initialize the list of actions
        actionList = new Action[]{new VacuumCleanerAction(VacuumCleanerAction.Type.LEFT),
                new VacuumCleanerAction(VacuumCleanerAction.Type.RIGHT),
                new VacuumCleanerAction(VacuumCleanerAction.Type.CLEAN)};
    }

    public Action[] actions(State st){
        //There's no need to dynamically generate the list each time since all actions can be applied to
        //any state. We simply return the precomputed list.
        return actionList;
    }


    @Override
    public boolean isGoal(State st) {
        return ((VacuumCleanerState) st).dirtPosition == VacuumCleanerState.DirtPosition.NONE;
    }
}
