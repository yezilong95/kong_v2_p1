import Vision
import AppKit

let paths = CommandLine.arguments.dropFirst()
for arg in paths {
    print("\n=== \(arg) ===")
    guard let img = NSImage(contentsOfFile: arg),
          let tiff = img.tiffRepresentation,
          let rep = NSBitmapImageRep(data: tiff),
          let cg = rep.cgImage else {
        print("FAIL load")
        continue
    }
    let sem = DispatchSemaphore(value: 0)
    let req = VNRecognizeTextRequest { req, _ in
        defer { sem.signal() }
        guard let obs = req.results as? [VNRecognizedTextObservation] else { return }
        for o in obs.sorted(by: { $0.boundingBox.origin.y > $1.boundingBox.origin.y }) {
            let t = o.topCandidates(1).first?.string ?? ""
            let b = o.boundingBox
            print(String(format: "  y=%.3f x=%.3f | %@", b.origin.y, b.origin.x, t))
        }
    }
    req.recognitionLevel = .accurate
    req.usesLanguageCorrection = true
    try? VNImageRequestHandler(cgImage: cg, options: [:]).perform([req])
    sem.wait()
}
