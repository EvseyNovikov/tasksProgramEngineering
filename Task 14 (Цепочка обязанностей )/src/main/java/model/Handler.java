package model;

/** throw <tt> LifeGameException</tt> might be thrown.*/
/** It is wrong to write a program that depended on an*/
/** exception for its correctness:  <i> the fail-fast behavior*/
/** should not be used </i>*/

public abstract class Handler  {
    private Handler processor;

    public Handler(Handler processor){
        this.processor = processor;
    }
    public boolean process(Integer request){

        if(processor != null)
            return processor.process(request);
        else
            return true;

    }
}