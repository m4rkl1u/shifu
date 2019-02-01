/*
 * Copyright [2013-2019] PayPal Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ml.shifu.shifu.core.dtrain.wnd;

/**
 * TODO
 * 
 * @author pengzhang
 */
public class DenseLayer implements Layer<float[], float[], float[], float[]> {

    private float[][] weights;

    private float[] bias;

    private int out;

    private int in;

    private float l2reg;

    // TODO l2 reg add
    public DenseLayer(float[][] weights, float[] bias, int out, int in, float l2reg) {
        this.weights = weights;
        this.bias = bias;
        this.out = out;
        this.in = in;
        this.setL2reg(l2reg);
    }

    public DenseLayer(int out, int in, float l2reg) {
        this.out = out;
        this.in = in;
        this.setL2reg(l2reg);
        // TODO init weights and bias
    }

    /**
     * @return the weights
     */
    public float[][] getWeights() {
        return weights;
    }

    /**
     * @param weights
     *            the weights to set
     */
    public void setWeights(float[][] weights) {
        this.weights = weights;
    }

    /**
     * @return the bias
     */
    public float[] getBias() {
        return bias;
    }

    /**
     * @param bias
     *            the bias to set
     */
    public void setBias(float[] bias) {
        this.bias = bias;
    }

    /**
     * @return the out
     */
    public int getOut() {
        return out;
    }

    /**
     * @param out
     *            the out to set
     */
    public void setOut(int out) {
        this.out = out;
    }

    /**
     * @return the in
     */
    public int getIn() {
        return in;
    }

    /**
     * @param in
     *            the in to set
     */
    public void setIn(int in) {
        this.in = in;
    }

    @Override
    public int getOutDim() {
        return this.out;
    }

    /**
     * @return the l2reg
     */
    public float getL2reg() {
        return l2reg;
    }

    /**
     * @param l2reg the l2reg to set
     */
    public void setL2reg(float l2reg) {
        this.l2reg = l2reg;
    }

    @Override
    public float[] forward(float[] inputs) {
        // TODO assert here
        float[] results = new float[this.out];
        for(int i = 0; i < results.length; i++) {
            for(int j = 0; j < inputs.length; j++) {
                results[i] += inputs[j] * this.weights[j][i];
            }
            results[i] += bias[i];
        }
        return results;
    }

    @Override
    public float[] backward(float[] backInputs, float sig) {
        // TODO gradients compute and L2 reg here
        float[] results = new float[this.in];
        for(int i = 0; i < this.in; i++) {
            for(int j = 0; j < backInputs.length; j++) {
                results[i] += backInputs[j] * this.weights[i][j];
            }
        }

        return results;
    }

}
