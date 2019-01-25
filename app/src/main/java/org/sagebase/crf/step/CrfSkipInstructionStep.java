/*
 *    Copyright 2018 Sage Bionetworks
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package org.sagebase.crf.step;

import android.support.annotation.Nullable;


import org.researchstack.backbone.result.StepResult;
import org.researchstack.backbone.result.TaskResult;
import org.researchstack.backbone.task.NavigableOrderedTask;

import java.util.List;

public class CrfSkipInstructionStep extends CrfInstructionStep
        implements NavigableOrderedTask.NavigationSkipRule, NavigableOrderedTask.NavigationRule {

    public String skipIdentifier;
    public String stepIdentifier;

    /**
     * The type of button to show
     */
    public CrfInstructionButtonType buttonType;

    /**
     * When buttonType is DEFAULT, this will be used as the title on the button
     * This can also be used in conjunction with other button types
     */
    public String buttonText;
    public String instruction;
    public boolean continueMeasurement = false;

    /* Default constructor needed for serialization/deserialization of object */
    public CrfSkipInstructionStep() {
        super();
    }

    public CrfSkipInstructionStep(String identifier, String title) {
        super(identifier, title, null);
    }

    @Override
    public boolean shouldSkipStep(@Nullable TaskResult result,
                                  @Nullable List<TaskResult> additionalTaskResults) {
        System.out.println("Got to the shouldSkipStep method");
        if ((StepResult<Boolean>) result.getStepResult("camera").getResultForIdentifier(skipIdentifier) == null) {
            return true;
        }

        StepResult<Boolean> res = (StepResult<Boolean>)
                result.getStepResult("camera").getResultForIdentifier(skipIdentifier);

        // If you are not skipping this step, the next step needs to continue measurement
        if (!res.getResult()) {
            continueMeasurement = true;
        }
        return res.getResult();
    }

    @Override
    public String nextStepIdentifier(TaskResult result, List<TaskResult> additionalTaskResults) {
        if(continueMeasurement) {
            return "instructionCamera";
        }
        return "abnormal_hrForm";
    }

    @Override
    public Class getStepLayoutClass() {
        return CrfSkipInstructionStepLayout.class;
    }

}
