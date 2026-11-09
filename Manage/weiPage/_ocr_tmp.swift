
import Vision, AppKit
let path = "/Volumes/data/CodeItems/MyItems/Node/kotlin_android_AI_v1/Manage/weiPage/_ref_2x.jpg"
guard let img = NSImage(contentsOfFile: path), let tiff = img.tiffRepresentation, let rep = NSBitmapImageRep(data: tiff), let cg = rep.cgImage else { fputs("FAIL\n", stderr); exit(1) }
let sem = DispatchSemaphore(value: 0)
let req = VNRecognizeTextRequest { req, _ in
    defer { sem.signal() }
    guard let obs = req.results as? [VNRecognizedTextObservation] else { return }
    for o in obs.sorted(by: { $0.boundingBox.origin.y > $1.boundingBox.origin.y }) {
        let t = o.topCandidates(1).first?.string ?? ""
        let b = o.boundingBox
        print(String(format: "%.3f|%.3f|%.3f|%.3f|%@", b.origin.y, b.origin.x, b.size.width, b.size.height, t))
    }
}
req.recognitionLevel = .accurate
req.usesLanguageCorrection = true
try? VNImageRequestHandler(cgImage: cg, options: [:]).perform([req])
sem.wait()
