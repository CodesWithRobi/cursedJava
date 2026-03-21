void main() throws IOException{
    double signalStrength = 0.5;
    if (signalStrength < 1) {
        // The "throw" is the actual event
        throw new IOException("Signal too weak!"); 
    }
}
