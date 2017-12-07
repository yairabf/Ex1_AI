public class IdsAlgorithm implements ISearcher{
    @Override
    public ISolution Search(ISearchable searchable) {
        for(int i = 0; i < searchable.getSize(); i++) {
            State found = dls(searchable.getInitialState(), i);
            if (found != null)
                return null;
        }
        return null;
    }
        /**
         * function IDDFS(root)
         for depth from 0 to ∞
         found ← DLS(root, depth)
         if found ≠ null
         return found

         function DLS(node, depth)
         if depth = 0 and node is a goal
         return node
         if depth > 0
         foreach child of node
         found ← DLS(child, depth−1)
         if found ≠ null
         return found
         return null
         */

    private State dls(State state, int depth) {
        if (depth == 0 && state.equals("G")){
            return state;
        }
        if (depth > 0){

        }
    }


}
